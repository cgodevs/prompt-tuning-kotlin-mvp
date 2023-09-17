package com.example.material3components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontVariation.width
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import br.com.fiap.startupfiap.R
import br.com.fiap.startupfiap.components.DrawerItem
import br.com.fiap.startupfiap.components.TopMenu
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenBase(
    screenTitle: String,
    innerContent: @Composable()() -> Unit,
    nav: NavHostController,
    drawerState: DrawerState,
    scope: CoroutineScope
) {
    val items = listOf(
        DrawerItem(
            label = "Explorar",
            icon = painterResource(id = R.drawable.analyze),
            route = "explore"
        ),
        DrawerItem(
            label = "Análise pontual",
            icon = painterResource(id = R.drawable.explore),
            route = "analyze"
        )
    )

    val selectedItemIndex = items.filter{it -> it.route == nav.currentDestination?.route}[0]

    var selectedItem by remember { mutableStateOf(selectedItemIndex) }

    ModalNavigationDrawer(
        gesturesEnabled = drawerState.isOpen,
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier
                    .width(254.dp),
                drawerContainerColor = colorResource(R.color.neutral00)
            ) {
                Box(
                    modifier = Modifier
                        .width(253.dp)
                        .height(65.dp)
                        .background(colorResource(R.color.neutral02))
                        .shadow(
                            elevation = 4.dp,
                            spotColor = colorResource(R.color.neutral08),
                            ambientColor = colorResource(R.color.neutral08)
                        ),
                    contentAlignment = Alignment.CenterStart
                ) {
                    Row() {
                        Spacer(modifier = Modifier.width(17.dp))

                        Image(
                            painter = painterResource(R.drawable.user),
                            contentDescription = "Ícone usuário",
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .width(40.25.dp)
                                .height(40.25.dp)
                        )

                        Spacer(modifier = Modifier.width(9.75.dp))

                        Text(
                            text = "Olá, usuário",
                            style = TextStyle(
                                fontSize = 28.sp,
                                fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                fontWeight = FontWeight(600),
                                color = colorResource(R.color.neutral08),
                            )
                        )
                    }
                }
                Spacer(Modifier.height(22.32.dp))

                items.forEach { item ->
                    NavigationDrawerItem(
                        colors = NavigationDrawerItemDefaults.colors(
                            selectedContainerColor = colorResource(R.color.neutral00),
                            unselectedContainerColor = colorResource(R.color.neutral00),
                            selectedTextColor = colorResource(R.color.neutral08),
                            unselectedTextColor = colorResource(R.color.neutral08),
                            selectedIconColor = colorResource(R.color.neutral08),
                            unselectedIconColor = colorResource(R.color.neutral08)
                        ),
                        shape = RoundedCornerShape(4.dp),
                        icon = {
                            Icon(
                                painter = item.icon,
                                contentDescription = item.label,
                                modifier = Modifier
                                    .width(40.25.dp)
                                    .height(40.8179.dp)
                            )
                       },
                        label = {
                            Text(
                                item.label,
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    fontFamily = FontFamily(Font(R.font.roboto_regular)),
                                    fontWeight = FontWeight(600),
                                    color = colorResource(R.color.neutral08),
                                )
                            )
                        },
                        selected = item == selectedItem,
                        onClick = {
                            scope.launch { drawerState.close() }
                            selectedItem = item
                            nav.navigate(item.route)
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)
                    )
                }
            }
        },
    ) {
        Scaffold(
            topBar = {
                TopMenu(screenTitle = screenTitle, nav = nav, scope = scope, drawerState = drawerState)
            }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                innerContent()
            }
        }
    }
}


