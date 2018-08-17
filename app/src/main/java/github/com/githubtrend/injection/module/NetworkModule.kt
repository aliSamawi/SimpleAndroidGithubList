package ir.r3za13.kotlin_rz_boilerplate.injection.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import ir.r3za13.kotlin_rz_boilerplate.BuildConfig
import ir.r3za13.kotlin_rz_boilerplate.data.restful.APIs
import ir.r3za13.kotlin_rz_boilerplate.data.restful.APIsWithToken
import ir.r3za13.kotlin_rz_boilerplate.injection.qualifier.WithToken
import ir.r3za13.kotlin_rz_boilerplate.injection.qualifier.WithoutToken
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * Created by Moosa_Abedini on 29/8/17.
 */
@Module
open class NetworkModule {


    @Provides
    @Singleton
    fun provideGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    @WithoutToken
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
    @WithToken
    fun provideOkHttpClientWithToken(/*accountManager: AccountManager*/): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val okHttpClient = OkHttpClient.Builder().apply {
            interceptors().add(Interceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
//                        .header("Authorization",AccountManager.TOKEN_PREFIX+ accountManager.getToken()!!)
//                    .addHeader(USER_AGENT_HEADER, USER_AGENT_VALUE)
                        .method(original.method(), original.body())
                val request = requestBuilder.build()
                chain.proceed(request)
            })

        }
                .addInterceptor(interceptor).build()
        return okHttpClient
    }

    @Provides
    @Singleton
    @WithoutToken
    fun provideRetrofit(@WithoutToken okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder().client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .build()
    }

    @Provides
    @Singleton
    @WithToken
    fun provideRetrofitWithToken(@WithToken okHttpClient: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder().client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BuildConfig.BASE_URL)
                .build()
    }

    @Provides
    @Singleton
    @WithToken
    open fun provideServiceWithToken(@WithToken retrofit: Retrofit): APIsWithToken = retrofit.create(APIsWithToken::class.java)

    @Provides
    @Singleton
    @WithoutToken
    open fun provideService(@WithoutToken retrofit: Retrofit): APIs = retrofit.create(APIs::class.java)

//    @Provides
//    @Singleton
//    open fun provideRepository(): CloudRepositoryContract = CloudRepository()

}