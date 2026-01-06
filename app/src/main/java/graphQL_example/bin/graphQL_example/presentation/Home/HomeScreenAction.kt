package graphQL_example.bin.graphQL_example.graphQL_example.bin.graphQL_example.presentation.Home


sealed interface HomeScreenAction {
    object AlertDismissed : HomeScreenAction
    object LoadData : HomeScreenAction

    data class InternetStatusChanged(
        val isInternetOff: Boolean
    ) : HomeScreenAction
}