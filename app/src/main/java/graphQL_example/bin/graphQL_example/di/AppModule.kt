package graphQL_example.bin.graphQL_example.di

import org.koin.dsl.module
import graphQL_example.bin.graphQL_example.core.ResourcesProvider

val appModule = module {
    single {
        ResourcesProvider(get())
    }
}
