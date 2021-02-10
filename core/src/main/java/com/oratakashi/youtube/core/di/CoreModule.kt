package com.oratakashi.youtube.core.di

import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.oratakashi.youtube.core.BuildConfig
import com.oratakashi.youtube.core.BuildConfig.BASE_URL
import com.oratakashi.youtube.core.BuildConfig.DOMAIN
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
import okhttp3.logging.HttpLoggingInterceptor.Level
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
                certificatePinner(
                    CertificatePinner.Builder()
                        .add(DOMAIN, "sha256/CbnjqjxEJ80gtjbhZp4MLe8+6a99ZweUM4te93gQSfY=")
                        .add(DOMAIN, "sha256/6uD6luGCoq+QjL1ONKLMPp3M+TD/qNPcl2esQIKyAsE=")
                        .add(DOMAIN, "sha256/9WxeoOnY+k/hGeN3QHGWodl6/1PUkaQsUHXmEuKrTVI=")
                        .add(DOMAIN, "sha256/uzHSo4am8R/lVc3TUzYfDeCKysJDuoTE5ejw/KKJscE=")
                        .add(DOMAIN, "sha256/qUyegvl9IEcp4Dsw5mAnJsy3FLg/x8TM+S0cy5NlEdk=")
                        .add(DOMAIN, "sha256/MXCwOn0ZY5FXx/FAssfigQXHqJ9XoErm0/CYQVRUSMA=")
                        .build()
                )
                addInterceptor(
                    Interceptor { chain ->
                        var request: Request = chain.request()
                        val url: HttpUrl = request.url.newBuilder()
                            .addQueryParameter("key", Config.key)
                            .build()
                        request = request.newBuilder().url(url).build()
                        chain.proceed(request)
                    }
                )
                if (BuildConfig.DEBUG) addInterceptor(
                    ChuckerInterceptor(androidContext())
                )
                addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = when (BuildConfig.DEBUG) {
                            true -> Level.BODY
                            false -> Level.NONE
                        }
                    }
                )
            }.build()
        }
        val provideHttpAdapter = factory {
            val retrofit = Retrofit.Builder().apply {
                client(get())
                baseUrl(BASE_URL)
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