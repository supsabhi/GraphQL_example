package graphQL_example.bin.graphQL_example.data.network_util


import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.features.HttpRedirect
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import graphQL_example.bin.graphQL_example.domain.network_util.HomeScreenAPI
import graphQL_example.bin.graphQL_example.graphQL_example.bin.graphQL_example.domain.model.ApiResult
import graphQL_example.bin.graphQL_example.graphQL_example.bin.graphQL_example.domain.model.CountriesData
import graphQL_example.bin.graphQL_example.graphQL_example.bin.graphQL_example.domain.model.CountryDataModel
import graphQL_example.bin.graphQL_example.graphQL_example.bin.graphQL_example.domain.model.GraphQLRequest
import graphQL_example.bin.graphQL_example.graphQL_example.bin.graphQL_example.domain.model.GraphQLResponse
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType


class HomeScreenApiImpl(
    private val client: HttpClient
) : HomeScreenAPI {

    override suspend fun getCountryData(
        url: String,
        query: String
    ): ApiResult<List<CountryDataModel>> {

        return try {
            val response: GraphQLResponse<CountriesData> =
                client.post(url) {
                    contentType(ContentType.Application.Json)
                    body = GraphQLRequest(query)
                }

            when {
                !response.errors.isNullOrEmpty() ->
                    ApiResult.Error(
                        response.errors.joinToString { it.message }
                    )

                response.data != null ->
                    ApiResult.Success(response.data.countries)

                else ->
                    ApiResult.Error("Empty GraphQL response")
            }

        } catch (e: Exception) {
            ApiResult.Error(e.message ?: "Network error")
        }
    }
}
