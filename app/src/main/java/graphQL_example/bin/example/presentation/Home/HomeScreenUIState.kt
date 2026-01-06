package graphQL_example.bin.example.graphQL_example.bin.graphQL_example.presentation.Home

import graphQL_example.bin.example.domain.model.MainUIState
import graphQL_example.bin.example.graphQL_example.bin.graphQL_example.domain.model.CountryDataModel

data class HomeScreenUIState(
    val uiState: MainUIState = MainUIState(),
    val internetOff: Boolean = false,
    val alert: AlertState? = null,

    val countryList: List<CountryDataModel> = emptyList(),

    val error: String? = null
)

data class AlertState(
    val title: String,
    val message: String,
    val action: AlertAction = AlertAction.NONE
)

enum class AlertAction {
    NONE,
    OPEN_WIFI_SETTINGS,
    EXIT_APP

}