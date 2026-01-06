package graphQL_example.bin.graphQL_example.domain.network_util

import graphQL_example.bin.graphQL_example.graphQL_example.bin.graphQL_example.domain.model.ApiResult
import graphQL_example.bin.graphQL_example.graphQL_example.bin.graphQL_example.domain.model.CountryDataModel


interface HomeScreenAPI {
    suspend fun getCountryData(url: String,query:String): ApiResult<List<CountryDataModel>>


}