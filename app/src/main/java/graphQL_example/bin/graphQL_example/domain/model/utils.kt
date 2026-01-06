package graphQL_example.bin.graphQL_example.domain.model

data class MainUIState(
    val isLoading: Boolean = false,
    val message: String? = null,
    val error: String? = null,
    val code: String? = null,
)