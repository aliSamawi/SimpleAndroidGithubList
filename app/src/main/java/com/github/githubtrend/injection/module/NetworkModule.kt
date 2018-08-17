package com.github.githubtrend.injection.module

import com.github.githubtrend.BuildConfig
import com.github.githubtrend.data.restful.APIs
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
* Created by ali on 8/17/2018 AD.
*/
@Module
class NetworkModule {


    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder().apply {
            interceptors().add(Interceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                        .method(original.method(), original.body())
                val request = requestBuilder.build()
                chain.proceed(request)
            })

        }.addInterceptor(interceptor).build()
        return okHttpClient
    }



    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder().client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .build()
    }

    @Provides
    @Singleton
    open fun provideService(retrofit: Retrofit): APIs = retrofit.create(APIs::class.java)

}