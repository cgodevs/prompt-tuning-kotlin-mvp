package br.com.fiap.startupfiap.screens

import android.os.Environment
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.fiap.startupfiap.R
import br.com.fiap.startupfiap.tools.scanSystemForFiles

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PunctualAnalysisScreen(
    nav: NavHostController
){
    val allDownloadedFiles = scanSystemForFiles(
        Environment.getExternalStoragePublicDirectory(
            Environment.DIRECTORY_DOWNLOADS
        ).absolutePath
    )
    allDownloadedFiles.forEach{(ext, files) ->
        Log.d("StartupFIAPLog", "Extension: $ext | Files: $files")
    }

    var gptAnswer by remember { mutableStateOf("GPT Answer Test") }

    var availableFileExtensions = userFiles.map{it.first}
    var selectedTabIndex by remember { mutableStateOf(0) }
    var selectedTabName by remember { mutableStateOf(availableFileExtensions.getOrElse(selectedTabIndex) { "" }) }
    var selectedExtensionFiles = userFiles.find { it.first == selectedTabName }?.second ?: emptyList()
//    var selectedFile = remember { mutableStateOf(0) }

    var selectedRadioIndex by remember { mutableStateOf(-1) }
    var userCommand = remember {mutableStateOf("")}

    Column(modifier = Modifier.fillMaxWidth()){

        CustomScrollableTabRow(
            tabs = availableFileExtensions,
            selectedTabIndex = selectedTabIndex,
            onTabClick = { clickedTabIndex ->
                selectedTabIndex = clickedTabIndex
                selectedTabName = availableFileExtensions.getOrElse(clickedTabIndex) { "" }
                selectedExtensionFiles = userFiles.find { it.first == selectedTabName }?.second ?: emptyList()
                selectedRadioIndex = -1
            }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .align(Alignment.TopCenter)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    fontFamily = FontFamily(Font(R.font.roboto_italic)),
                    text = "Choose a file: "
                )
                Spacer(modifier = Modifier.height(8.dp))

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth(),
                    userScrollEnabled = true
                ) {
                    items(selectedExtensionFiles) { item ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            val selectedIndex = selectedExtensionFiles.indexOf(item)
                            RadioButton(
                                selected = selectedRadioIndex == selectedIndex,
                                onClick = {
                                    selectedRadioIndex = selectedIndex
                                },
                                modifier = Modifier
                                    .padding(end = 8.dp)
                            )
                            Text(text = item)
                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
                    .padding(4.dp)
                    .height(100.dp)
                    .align(Alignment.BottomCenter),
            ) {
                OutlinedTextField(
                    value = "${userCommand.value}",
                    onValueChange = { it ->
                        userCommand.value = it
                    },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                    placeholder = {
                        Text(text = "Ask a question or send a command")
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    trailingIcon = {
                        IconButton(
                            enabled = userCommand.value != "" && selectedRadioIndex != -1,
                            onClick = {
                                nav.navigate(route="gpt_answer/${gptAnswer}")
                            },
                        ) {
                            Icon(
                                imageVector = Icons.Default.Send,
                                contentDescription = "Send Icon",
                            )
                        }
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        textColor = Color.Black
                    )
                )
            }
        }
    }
}

@Composable
fun CustomScrollableTabRow(
    tabs: List<String>,
    selectedTabIndex: Int,
    onTabClick: (Int) -> Unit
) {
    ScrollableTabRow(
        selectedTabIndex = selectedTabIndex,
        contentColor = Color.Black,
        edgePadding = 0.dp,
    ) {
        tabs.forEachIndexed { tabIndex, tab ->
            Tab(
                selected = selectedTabIndex == tabIndex,
                onClick = { onTabClick(tabIndex)},
                text = { Text(text = tab) }
            )
        }
    }
}

public val userFiles = listOf(
    Pair("doc", listOf<String>(
        "Fiap Project.doc",
        "Project 0.doc",
        "Project 1.doc",
        "Project 2.doc",
        "Project 3.doc",
        "Project 4.doc",
        "Project 5.doc",
        "Project 6.doc",
        "Project 7.doc",
        "Project 8.doc",
        "Project 9.doc",
        "Project 10.doc",
        "Project 11.doc",
        "Project 12.doc",
        "Project 13.doc",
        "Project 14.doc",
        "Project 15.doc",
        "Project 16.doc",
        "Project 17.doc")
    ),
    Pair("pdf", listOf<String>(
        "My PDF File 0.pdf",
        "My PDF File 1.pdf",
        "My PDF File 2.pdf",
        "My PDF File 3.pdf",
        "My PDF File 4.pdf",
        "My PDF File 5.pdf",
        "My PDF File 6.pdf")
    ),
    Pair("txt", listOf<String>(
        "My TXT File 0.txt",
        "My TXT File 1.txt",
        "My TXT File 2.txt",
        "My TXT File 3.txt",
        "My TXT File 4.txt",
        "My TXT File 5.txt",
        "My TXT File 6.txt")
    ),
    Pair("xlsx", listOf<String>(
        "My XLSX File 0.xlsx",
        "My XLSX File 1.xlsx",
        "My XLSX File 2.xlsx",
        "My XLSX File 3.xlsx",
        "My XLSX File 4.xlsx",
        "My XLSX File 5.xlsx",
        "My XLSX File 6.xlsx")
    ),
    Pair("png", listOf<String>(
        "My PNG File 0.png",
        "My PNG File 1.png",
        "My PNG File 2.png",
        "My PNG File 3.png",
        "My PNG File 4.png",
        "My PNG File 5.png",
        "My PNG File 6.png")
    )
)
