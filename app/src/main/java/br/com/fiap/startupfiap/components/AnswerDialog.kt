package br.com.fiap.startupfiap.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import br.com.fiap.startupfiap.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnswerDialog(
    onConfirmation: () -> Unit,
    dialogTitle: String = "Answer",
    dialogText: String,
    isOpen: Boolean
//    icon: ImageVector,
) {
    if(isOpen){
        AlertDialog(
    //        icon = {
    //            Icon(icon, contentDescription = "Example Icon")
    //        },
            modifier = Modifier
                .height(480.dp),
            containerColor = colorResource(R.color.TopMenuGray),
            title = {
                Text(text = dialogTitle)
            },
            text = {
                Text(
                    modifier = Modifier.verticalScroll(rememberScrollState()),
                    text = dialogText
                )
            },
            onDismissRequest = {
    //            onDismissRequest()
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onConfirmation()
                    },
                    content = {Text("OK")}
                )
            }
        )
    }
}

@Composable
fun MinimalDialog(onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Text(
                text = "This is a minimal dialog",
                modifier = Modifier
                    .fillMaxSize()
                    .wrapContentSize(Alignment.Center),
                textAlign = TextAlign.Center,
            )
        }
    }
}