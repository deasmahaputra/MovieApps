package com.deas.movieapps.di.module

import com.deas.movieapps.BuildConfig
import com.deas.movieapps.network.ApiService
import com.deas.movieapps.network.HttpHeaderInterceptor
import com.deas.movieapps.network.NetworkService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by igede@awantunai.com on 19/06/21.
 */

@Module
class NetworkModule {

    @Provides
    @Singleton
    @Suppress("ConstantConditionIf")
    fun providesHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val loggingLevel =
            if (BuildConfig.BUILD_TYPE == "release") HttpLoggingInterceptor.Level.NONE else HttpLoggingInterceptor.Level.BODY
        return HttpLoggingInterceptor().setLevel(loggingLevel)
    }

    @Provides
    @Singleton
    fun providesHttpHeaderInterceptor(
    ): HttpHeaderInterceptor = HttpHeaderInterceptor()


    @Provides
    @Singleton
    fun providesOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        headerInterceptor: HttpHeaderInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .addInterceptor(loggingInterceptor)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .build()
    }

    @Provides
    @Singleton
    @Named("NonHeaderOkHttp")
    fun providesNonHeaderOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.ENDPOINT)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    @Named("NonHeaderRetrofit")
    fun providesNonHeaderRetrofit(@Named("NonHeaderOkHttp") okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.ENDPOINT)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun providesNetworkService(retrofit: Retrofit): NetworkService = retrofit.create(
        NetworkService::class.java
    )

    @Provides
    @Singleton
    fun providesApiService(networkService: NetworkService): ApiService = ApiService(networkService)

}