package com.oratakashi.youtube.core.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.oratakashi.youtube.core.BuildConfig
import com.oratakashi.youtube.core.Config
import com.oratakashi.youtube.data.database.RoomDB
import com.oratakashi.youtube.data.network.ApiEndpoint
import com.oratakashi.youtube.data.repository.DataRepository
import com.oratakashi.youtube.data.repository.local.LocalRepository
import com.oratakashi.youtube.data.repository.remote.RemoteRepository
import com.oratakashi.youtube.domain.interactor.Interactor
import com.oratakashi.youtube.domain.repository.Repository
import com.oratakashi.youtube.domain.usecase.UseCase
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object CoreModule {
    val networkModule = module {
        val provideHttpClient = factory {
            OkHttpClient.Builder().apply {
                retryOnConnectionFailure(true)
                readTimeout(30, TimeUnit.SECONDS)
                writeTimeout(30, TimeUnit.SECONDS)
                addInterceptor(
                    object : Interceptor {
                        override fun intercept(chain: Interceptor.Chain): Response {
                            var request: Request = chain.request()
                            val url: HttpUrl = request.url.newBuilder()
                                .addQueryParameter("key", Config.key)
                                .build()
                            request = request.newBuilder().url(url).build()
                            return chain.proceed(request)
                        }
                    }
                )
                if (BuildConfig.DEBUG) addInterceptor(
                    ChuckerInterceptor(androidContext())
                )
                addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = when (BuildConfig.DEBUG) {
                            true -> HttpLoggingInterceptor.Level.BODY
                            false -> HttpLoggingInterceptor.Level.NONE
                        }
                    }
                )
            }.build()
        }
        val provideHttpAdapter = factory {
            val retrofit = Retrofit.Builder().apply {
                client(get())
                baseUrl(BuildConfig.BASE_URL)
                addConverterFactory(GsonConverterFactory.create())
                addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            }.build()
            retrofit.create(ApiEndpoint::class.java)
        }
    }
    val repositoryModule = module {
        val provideDatabase = factory {
            RoomDB(androidContext())
        }
        val provideLocalRepository = factory {
            LocalRepository(
                get(),
                androidContext()
            )
        }
        val provideRemoteRepository = factory {
            RemoteRepository(
                get(),
                androidContext()
            )
        }
        val provideRepository = factory<Repository> {
            DataRepository(
                get(),
                get()
            )
        }
    }
    val domainModule = module {
        val provideUseCase = factory<UseCase> {
            Interactor(get())
        }
    }
}