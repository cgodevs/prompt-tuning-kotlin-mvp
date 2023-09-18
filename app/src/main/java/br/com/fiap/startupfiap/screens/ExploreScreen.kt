package br.com.fiap.startupfiap.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import br.com.fiap.startupfiap.components.GPTAnswerSpace

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreen(){
    var userCommand = remember {
        mutableStateOf("")
    }
    var gptAnswer = "Screen under construction.\n\n" +
            "Its functionality allows an user to explore and analyze or command actions over a set of their files in batch, all at once.\n\n" +
            "This space is dedicated to the intelligent assistant comeback on the matter."

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        GPTAnswerSpace(
            content = "$gptAnswer",
            modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .align(Alignment.TopCenter)
        )
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
                        enabled = false, //userCommand.value != "",
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