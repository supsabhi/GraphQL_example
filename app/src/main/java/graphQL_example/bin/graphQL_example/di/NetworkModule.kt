package graphQL_example.bin.graphQL_example.di

import android.content.Context
import android.util.Log
import graphQL_example.bin.graphQL_example.data.network_util.HomeScreenApiImpl

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.request.HttpRequestBuilder

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.serialization.ExperimentalSerializationApi
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.HttpTimeout
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.features.observer.ResponseObserver
import io.ktor.client.request.accept
import io.ktor.http.ContentType
import io.ktor.http.contentType
import kotlinx.serialization.json.Json
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import graphQL_example.bin.graphQL_example.domain.network_util.HomeScreenAPI


val authInterceptor: suspend HttpRequestBuilder.(
    Context, CoroutineScope
) -> Unit = { context, scope ->

}

@OptIn(ExperimentalSerializationApi::class)
val networkModule = module {
    single {
        CoroutineScope(Dispatchers.IO + SupervisorJob())
    }
    single {
        HttpClient(Android) {
            install(JsonFeature) {
                serializer = KotlinxSerializer(Json {
                    prettyPrint = true
                    isLenient = true
                    explicitNulls = false
                    ignoreUnknownKeys = true
                    encodeDefaults = false
                    expectSuccess = false
                })
            }

            engine {
                connectTimeout = 60000
                socketTimeout = 60000
            }

            //Logging
            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.d("TAG", message)
                    }
                }
                level = LogLevel.ALL
            }

            //Http Response
            install(ResponseObserver) {
                onResponse { response ->
                    Log.d("HTTP status:", "${response.status.value}")
                }
            }

            // Headers
            install(DefaultRequest) {
                (get<CoroutineScope>()).launch {
                    authInterceptor(androidContext(), get())
                }
                contentType(ContentType.Application.Json)
                accept(ContentType.Application.Any)

            }

            install(HttpTimeout) {
                requestTimeoutMillis = 60000
                connectTimeoutMillis = 60000
                socketTimeoutMillis = 60000
            }
        }
    }

    // Ktor
    factory<HomeScreenAPI> { HomeScreenApiImpl(get()) }
}