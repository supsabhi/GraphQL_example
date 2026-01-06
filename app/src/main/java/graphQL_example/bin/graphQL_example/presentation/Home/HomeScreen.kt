package graphQL_example.bin.graphQL_example.presentation.Home

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import graphQL_example.bin.graphQL_example.graphQL_example.bin.graphQL_example.presentation.Home.AlertAction
import graphQL_example.bin.graphQL_example.graphQL_example.bin.graphQL_example.presentation.Home.ComposeAlertDialog
import graphQL_example.bin.graphQL_example.graphQL_example.bin.graphQL_example.presentation.Home.CountryScreen
import graphQL_example.bin.graphQL_example.graphQL_example.bin.graphQL_example.presentation.Home.HomeScreenAction
import graphQL_example.bin.graphQL_example.graphQL_example.bin.graphQL_example.presentation.Home.HomeScreenUIState
import graphQL_example.bin.graphQL_example.graphQL_example.bin.graphQL_example.presentation.Home.HomeViewModel

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun HomeScreen(
    state: HomeScreenUIState,
    viewModel: HomeViewModel
) {
    val context = LocalContext.current
    val activity = context as? Activity
    LaunchedEffect(Unit) {
        viewModel.onAction(HomeScreenAction.LoadData)
    }
    Scaffold(
    )
    { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color.LightGray)
        ) {

            if (state.uiState.isLoading) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(50.dp),
                        color = Color.Black,
                        trackColor = Color.White
                    )
                }
            }
            state.alert?.let { alert ->
                ComposeAlertDialog(
                    title = alert.title,
                    msg = alert.message,
                    onButtonClick = {
                        viewModel.onAction(HomeScreenAction.AlertDismissed)

                        when (alert.action) {
                            AlertAction.NONE -> {}
                            AlertAction.EXIT_APP -> {
                                activity?.finishAffinity()
                            }

                            AlertAction.OPEN_WIFI_SETTINGS -> {
                                context.startActivity(
                                    Intent(Settings.ACTION_WIFI_SETTINGS)
                                )
                                activity?.finish()
                            }
                        }
                    },
                    onDismissClick = {}
                )
            }
            if (state.countryList.isNotEmpty()) {
                CountryScreen(state.countryList)
            }
        }
    }

}
