package graphQL_example.bin.example.data.network_util


import io.ktor.client.HttpClient
import graphQL_example.bin.example.domain.network_util.HomeScreenAPI
import graphQL_example.bin.example.graphQL_example.bin.graphQL_example.domain.model.ApiResult
import graphQL_example.bin.example.graphQL_example.bin.graphQL_example.domain.model.CountriesData
import graphQL_example.bin.example.graphQL_example.bin.graphQL_example.domain.model.CountryDataModel
import graphQL_example.bin.example.graphQL_example.bin.graphQL_example.domain.model.GraphQLRequest
import graphQL_example.bin.example.graphQL_example.bin.graphQL_example.domain.model.GraphQLResponse
import io.ktor.client.request.post
import io.ktor.http.ContentType
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
