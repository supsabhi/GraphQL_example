package graphQL_example.bin.example.graphQL_example.bin.graphQL_example.presentation.Home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import graphQL_example.bin.example.core.ResourcesProvider
import graphQL_example.bin.example.domain.model.MainUIState
import graphQL_example.bin.example.domain.network_util.Connectivity
import graphQL_example.bin.example.domain.repositories.HomeRepository
import graphQL_example.bin.example.graphQL_example.bin.graphQL_example.domain.model.ApiResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import test.bin.jetpackcompose_template.R

class HomeViewModel(
    private val homeScreenRepository: HomeRepository,
    private val resourcesProvider: ResourcesProvider,
    private val connectivity: Connectivity,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeScreenUIState())
    val uiState: StateFlow<HomeScreenUIState> = _uiState.asStateFlow()

    @RequiresApi(Build.VERSION_CODES.O)
    fun onAction(action: HomeScreenAction) {

        when (action) {
            HomeScreenAction.LoadData -> handleLoadData()
            HomeScreenAction.AlertDismissed -> clearAlert()
            is HomeScreenAction.InternetStatusChanged -> handleInternetStatus(action)

        }
    }


    @RequiresApi(Build.VERSION_CODES.O)
    private fun handleLoadData() {
        if (connectivity.hasNetwork()) {
            fetchGraphQL()
        } else {
            _uiState.update {
                it.copy(
                    internetOff = false,
                    alert = AlertState(
                        title = resourcesProvider.getString(R.string.net_off_title),
                        message = resourcesProvider.getString(R.string.net_on_msg),
                        action = AlertAction.OPEN_WIFI_SETTINGS
                    )
                )

            }
            return
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun fetchGraphQL() {
        if (!connectivity.hasNetwork()) {
            handleInternetDisabled()
            return
        }

        loadCountries()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun loadCountries() {
        viewModelScope.launch {
            if (!connectivity.hasNetwork()) {
                handleInternetDisabled()
                return@launch
            }

            _uiState.update {
                it.copy(uiState = MainUIState(isLoading = true))
            }
            val query = """
                query {
                  countries {
                    code
                    name
                    capital
                  }
                }
            """.trimIndent()
            when (val result = homeScreenRepository.getCountries(query)) {

                is ApiResult.Success -> {
                    _uiState.update {
                        it.copy(
                            uiState = MainUIState(isLoading = false),
                            countryList = result.data,
                            internetOff = false
                        )
                    }
                }

                is ApiResult.Error -> {
                    val errorMessage = result.message

                    _uiState.update {
                        it.copy(
                            uiState = MainUIState(
                                isLoading = false,
                                error = errorMessage
                            ),
                            internetOff = errorMessage ==
                                    resourcesProvider.getString(R.string.net_off_title),
                            alert = AlertState(
                                title = resourcesProvider.getString(R.string.api_error_title),
                                message = errorMessage,
                                action = AlertAction.EXIT_APP
                            )
                        )
                    }
                }
            }
        }
    }

    private fun clearAlert() {
        _uiState.update { it.copy(alert = null) }
    }


    private fun handleInternetStatus(action: HomeScreenAction.InternetStatusChanged) {
        _uiState.update {
            it.copy(
                internetOff = action.isInternetOff,
                alert = if (action.isInternetOff) {
                    AlertState(
                        title = resourcesProvider.getString(R.string.net_off_title),
                        message = resourcesProvider.getString(R.string.net_on_msg),
                        action = AlertAction.OPEN_WIFI_SETTINGS
                    )
                } else {
                    null
                }
            )
        }
    }

    fun handleInternetDisabled() {
        _uiState.update {
            it.copy(
                internetOff = true,
                alert = AlertState(
                    title = resourcesProvider.getString(R.string.net_off_title),
                    message = resourcesProvider.getString(R.string.net_on_msg),
                    action = AlertAction.OPEN_WIFI_SETTINGS
                )
            )
        }
    }
}
