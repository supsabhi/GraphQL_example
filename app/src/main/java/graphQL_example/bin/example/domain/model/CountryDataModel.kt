package graphQL_example.bin.example.graphQL_example.bin.graphQL_example.domain.model

import kotlinx.serialization.Serializable
@Serializable
data class CountriesData(
    val countries: List<CountryDataModel>
)

@Serializable
data class CountryDataModel(
    val code: String,
    val name: String,
    val capital: String? = null
)