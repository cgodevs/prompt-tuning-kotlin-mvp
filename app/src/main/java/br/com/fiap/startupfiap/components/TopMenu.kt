package br.com.fiap.startupfiap.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavHostController
import br.com.fiap.startupfiap.R
import br.com.fiap.startupfiap.tools.revertDrawerState
import kotlinx.coroutines.CoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopMenu(
    screenTitle: String,
    nav: NavHostController,
    scope: CoroutineScope,
    drawerState: DrawerState
){
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    TopAppBar(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = colorResource(R.color.TopMenuGray),
            titleContentColor = Color.Black
        ),
        title = {
            Text(
                screenTitle,
                fontFamily = FontFamily(Font(R.font.roboto_bold)),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(
                onClick = { revertDrawerState(scope, drawerState) },
                colors = IconButtonDefaults.iconButtonColors(contentColor = Color.Black)
            ) {
                Icon(
                    imageVector = Icons.Filled.Menu,
                    contentDescription = "Localized description"
                )                    }
        },
        actions = {
//            IconButton(
//                onClick = { nav.popBackStack() },
//                colors = IconButtonDefaults.iconButtonColors(contentColor = Color.Black)
//            ) {
//                Icon(
//                    imageVector = Icons.Filled.ArrowBack,
//                    contentDescription = "Localized description"
//                )
//            }
        },
        scrollBehavior = scrollBehavior,
    )
}