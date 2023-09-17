package br.com.fiap.startupfiap

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.fiap.startupfiap.components.*
import br.com.fiap.startupfiap.screens.*
import br.com.fiap.startupfiap.ui.theme.StartupFIAPTheme
import com.example.material3components.ScreenBase


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StartupFIAPTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    // --------------------- State Variables  -------------------------------
                    val nav = rememberNavController()
                    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                    val scope = rememberCoroutineScope()

                    // ------------------------- Navigation  -------------------------------
                    NavHost(
                        navController = nav,
                        startDestination = "analyze"
                    ) {
                        composable(route = "explore") {
                            ScreenBase(
                                screenTitle = "Explorar",
                                innerContent = { ExploreScreen() },
                                drawerState = drawerState,
                                scope = scope,
                                nav = nav
                            )
                        }
                        composable(route = "analyze") {
                            ScreenBase(
                                screenTitle = "Análise pontual",
                                innerContent = { PunctualAnalysisScreen() },
                                drawerState = drawerState,
                                scope = scope,
                                nav = nav
                            )
                        }
                    }
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    StartupFIAPTheme {
        val nav = rememberNavController()
        val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
        val scope = rememberCoroutineScope()
        ScreenBase(
            screenTitle = "Explore",
            innerContent = { ExploreScreen() },
            drawerState = drawerState,
            scope = scope,
            nav = nav
        )
    }
}