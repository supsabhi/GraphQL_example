package  graphQL_example.bin.graphQL_example.graphQL_example.bin.graphQL_example.presentation.navigation

sealed class Screen(val route: String) {
    object HomeScreen : Screen(HOMESCREEN)
}