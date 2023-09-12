package br.com.fiap.startupfiap.components

import androidx.compose.ui.graphics.painter.Painter

data class DrawerItem(
    val icon: Painter,
    val label: String,
    val route: String
)