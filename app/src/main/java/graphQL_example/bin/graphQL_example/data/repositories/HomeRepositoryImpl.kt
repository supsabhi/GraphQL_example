package graphQL_example.bin.graphQL_example.data.repositories

import graphQL_example.bin.graphQL_example.core.ResourcesProvider
import graphQL_example.bin.graphQL_example.domain.network_util.Connectivity
import graphQL_example.bin.graphQL_example.domain.network_util.HomeScreenAPI
import graphQL_example.bin.graphQL_example.domain.repositories.HomeRepository
import graphQL_example.bin.graphQL_example.graphQL_example.bin.graphQL_example.domain.model.ApiResult
import graphQL_example.bin.graphQL_example.graphQL_example.bin.graphQL_example.domain.model.CountryDataModel
import test.bin.jetpackcompose_template.R
import timber.log.Timber


class HomeRepositoryImpl(
    private val homeScreenAPI: HomeScreenAPI,
    private val connectivity: Connectivity,
    private val resourcesProvider: ResourcesProvider,
) : HomeRepository {
    override suspend fun getCountries(query: String): ApiResult<List<CountryDataModel>> {
        if (!connectivity.hasNetwork()) {
            return ApiResult.Error(
                resourcesProvider.getString(R.string.net_off_title)
            )
        }

        return try {
            val url = "https://countries.trevorblades.com/graphql"

            homeScreenAPI.getCountryData(
                url = url,
                query = query
            )

        } catch (e: Exception) {
            Timber.e(e, "API call failed")
            ApiResult.Error(e.message ?: "Unexpected error")
        }

    }
}

