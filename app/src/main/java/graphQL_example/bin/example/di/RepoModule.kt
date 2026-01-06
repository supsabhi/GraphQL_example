package graphQL_example.bin.example.di


import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import graphQL_example.bin.example.data.network_util.ConnectivityImpl
import graphQL_example.bin.example.data.repositories.HomeRepositoryImpl
import graphQL_example.bin.example.domain.network_util.Connectivity
import graphQL_example.bin.example.domain.repositories.HomeRepository


val repoModule = module {
    factory<Connectivity> { ConnectivityImpl(androidContext()) }
    factory<HomeRepository> {
        HomeRepositoryImpl(
            homeScreenAPI = get(),
            connectivity = get(),
            resourcesProvider = get(),
        )
    }
}