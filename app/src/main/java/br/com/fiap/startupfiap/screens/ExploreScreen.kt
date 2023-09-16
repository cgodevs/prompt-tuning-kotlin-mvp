package br.com.fiap.startupfiap.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.com.fiap.startupfiap.R
import br.com.fiap.startupfiap.components.Input

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExploreScreen(){
    var userCommand = remember {
        mutableStateOf("Em quantos % o balanço mensal da minha empresa, a Naskar, cresceu em relação a 2020?")
    }

    var gptAnswer = "No mês de agosto deste ano, o balanço mensal da Naskar registrou um incrível aumento de 52% em comparação com agosto de 2020. O balanço mensal em agosto de 2023 alcançou a marca de \$2.5 milhões, enquanto no mesmo período do ano anterior, em agosto de 2020, o balanço mensal foi de \$1.6 milhão."

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.neutral00)),
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
                text = "Moui:",
                style = TextStyle(
                    fontSize = 26.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                    fontWeight = FontWeight(1000),
                    color = colorResource(id = R.color.neutral08),
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "$gptAnswer",
                textAlign = TextAlign.Left,
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(vertical = 8.dp),
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                    fontWeight = FontWeight(400),
                    color = colorResource(id = R.color.neutral08),
                )
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(colorResource(id = R.color.neutral00))
                .padding(4.dp)
                .height(100.dp)
                .align(Alignment.BottomCenter),
        ){
            Input(
                value = "${userCommand.value}",
                placeholder = "Faça uma pergunta ou envie um comando",
                onChange = { it: String ->
                    userCommand.value = it
                },
                onSend = {}
            )
        }

    }
}