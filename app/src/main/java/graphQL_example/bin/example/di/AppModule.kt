package graphQL_example.bin.example.di

import org.koin.dsl.module
import graphQL_example.bin.example.core.ResourcesProvider

val appModule = module {
    single {
        ResourcesProvider(get())
    }
}
