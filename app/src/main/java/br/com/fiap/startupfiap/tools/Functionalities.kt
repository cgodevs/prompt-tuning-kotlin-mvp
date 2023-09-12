package br.com.fiap.startupfiap.tools

import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
fun revertDrawerState(scope: CoroutineScope, drawerState: DrawerState){
    if (drawerState.isClosed){
        scope.launch { drawerState.open() }
    } else {
        scope.launch { drawerState.close() }
    }
}
