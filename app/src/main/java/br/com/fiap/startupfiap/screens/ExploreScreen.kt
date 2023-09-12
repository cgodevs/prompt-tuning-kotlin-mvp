package br.com.fiap.startupfiap.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreen(){
    var userCommand = remember {
        mutableStateOf("")
    }
    var gptAnswer = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris elementum ante a justo sodales, at lacinia ex maximus. Phasellus quis libero consequat, imperdiet leo eget, aliquet risus. Aliquam venenatis pellentesque arcu non posuere. Nam nisi sapien, tristique id urna eget, maximus eleifend mi. In ullamcorper eros nec sodales finibus. Aliquam vestibulum feugiat elit in lacinia. Vivamus quis erat risus. Ut imperdiet nibh vel dignissim pharetra. Vivamus nec interdum dolor, ut cursus ipsum. Morbi tincidunt tortor diam, sit amet mollis risus facilisis ac. Nullam blandit ante a justo maximus, a venenatis tortor posuere. Vivamus convallis congue hendrerit. Cras pharetra ante est, eget congue eros viverra a. Duis ullamcorper libero quam, eget egestas dui molestie vitae. Integer porttitor, nunc quis suscipit finibus, ante risus finibus nunc, non tincidunt sem massa in leo. Cras suscipit molestie viverra. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris elementum ante a justo sodales, at lacinia ex maximus. Phasellus quis libero consequat, imperdiet leo eget, aliquet risus. Aliquam venenatis pellentesque arcu non posuere. Nam nisi sapien, tristique id urna eget, maximus eleifend mi. In ullamcorper eros nec sodales finibus. Aliquam vestibulum feugiat elit in lacinia. Vivamus quis erat risus. Ut imperdiet nibh vel dignissim pharetra. Vivamus nec interdum dolor, ut cursus ipsum. Morbi tincidunt tortor diam, sit amet mollis risus facilisis ac. Nullam blandit ante a justo maximus, a venenatis tortor posuere. Vivamus convallis congue hendrerit. Cras pharetra ante est, eget congue eros viverra a. Duis ullamcorper libero quam, eget egestas dui molestie vitae. Integer porttitor, nunc quis suscipit finibus, ante risus finibus nunc, non tincidunt sem massa in leo. Cras suscipit molestie viverra"

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(24.dp)
                    .align(Alignment.TopCenter)
//                    .height(400.dp)
        ) {
            Text(
                text = "Answer",
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "$gptAnswer",
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = 8.dp)
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Black)
                .padding(4.dp)
                .height(100.dp)
                .align(Alignment.BottomCenter),
        ){
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
                        enabled = userCommand.value != "",
                        onClick = {},
                    ){
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