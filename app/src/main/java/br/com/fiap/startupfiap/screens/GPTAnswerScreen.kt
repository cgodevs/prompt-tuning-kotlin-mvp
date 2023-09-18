package br.com.fiap.startupfiap.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import br.com.fiap.startupfiap.components.GPTAnswerSpace

@Composable
fun GPTAnswerScreen(nav: NavHostController){
    val arguments = nav.currentBackStackEntry?.arguments
    val filePath = arguments?.getString("filePath") ?: "No filePath"
    val command = arguments?.getString("command") ?: "No command"

//    val gptAssistant = GptAssistant()

    Column(
        modifier = Modifier.fillMaxSize()
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = { nav.popBackStack() }) {
                Icon(
                    imageVector = Icons.Outlined.ArrowBack,
                    contentDescription = "Back"
                )
            }
        }

        Row(modifier = Modifier.fillMaxWidth()){
            GPTAnswerSpace(
                content = "Example Answer"
            )
        }
//        Button(
//            onClick = {
//                Log.d("StartupFIAPLogs", gptAssistant.askGpt("tell a joke"))
//            }
//        ){
//            Text("click")
//        }
    }
}

