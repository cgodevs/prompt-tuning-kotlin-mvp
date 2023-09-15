package br.com.fiap.startupfiap.components
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.widget.Placeholder
import br.com.fiap.startupfiap.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Input(
    value: String,
    placeholder: String,
    onChange: (String) -> Unit,
    onSend: () -> Unit
) {
    val textFieldColors = TextFieldDefaults.outlinedTextFieldColors(
        unfocusedBorderColor = colorResource(R.color.neutral04),
        focusedBorderColor = colorResource(R.color.neutral06),
        placeholderColor = colorResource(R.color.neutral05),
        containerColor = colorResource(R.color.neutral00),
        textColor = colorResource(R.color.black),
        cursorColor = colorResource(R.color.neutral08),

        //        backgroundColor = Color.White, // Change background color
        //        leadingIconColor = Color.Green, // Change leading icon color
        //        trailingIconColor = Color.Yellow, // Change trailing icon color
        //        cursorPadding = PaddingValues(8.dp), // Adjust cursor padding
        //        errorCursorColor = Color.Red, // Change cursor color when there's an error
        //        errorLeadingIconColor = Color.Red, // Change leading icon color when there's an error
        //        errorTrailingIconColor = Color.Red, // Change trailing icon color when there's an error
    )

    OutlinedTextField(
        value = value,
        onValueChange = onChange,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        placeholder = {
            Text(text = placeholder)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        trailingIcon = {
            IconButton(
                enabled = value != "",
                onClick = onSend,
            ) {
                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Send Icon",
                )
            }
        },
        colors = textFieldColors
    )
}