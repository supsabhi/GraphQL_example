package graphQL_example.bin.example.domain.repositories

import graphQL_example.bin.example.graphQL_example.bin.graphQL_example.domain.model.ApiResult
import graphQL_example.bin.example.graphQL_example.bin.graphQL_example.domain.model.CountryDataModel


interface HomeRepository {
    suspend fun getCountries(query:String): ApiResult<List<CountryDataModel>>
}

