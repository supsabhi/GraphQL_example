package graphQL_example.bin.graphQL_example

import android.app.Application
import  graphQL_example.bin.graphQL_example.di.appModule
import graphQL_example.bin.graphQL_example.di.homeModule
import  graphQL_example.bin.graphQL_example.di.networkModule
import  graphQL_example.bin.graphQL_example.di.repoModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class GraphQLAPP : Application() {

    override fun onCreate() {
        super.onCreate()

        // Start Koin
        startKoin {
            androidLogger(Level.DEBUG) // Optional, for logging purposes
            androidContext(this@GraphQLAPP) // Application context for Koin
            modules(
                homeModule,
                appModule,
                networkModule,
                repoModule
            ) // modules
        }
    }
}