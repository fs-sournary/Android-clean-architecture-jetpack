package com.andrdoidlifelang.data.api

import com.andrdoidlifelang.data.BuildConfig
import com.andrdoidlifelang.data.util.Constant
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object ApiManager {

    fun getMovieApi(): MovieApi = getRetrofit().create(MovieApi::class.java)

    private fun getRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(getOkHttpClient())
        .build()

    private fun getOkHttpClient() = OkHttpClient.Builder()
        .readTimeout(Constant.API_PROCESS_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(Constant.API_PROCESS_TIMEOUT, TimeUnit.SECONDS)
        .connectTimeout(Constant.API_PROCESS_TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(getHeaderInterceptor())
        .addInterceptor(getLogInterceptor())
        .build()

    private fun getHeaderInterceptor(): Interceptor = Interceptor.invoke {
        val request = it.request()
        val newUrl = request.url
            .newBuilder()
            .addQueryParameter(Constant.API_KEY_PARAMS, BuildConfig.API_KEY)
            .build()
        val newRequest = request.newBuilder().apply { url(newUrl) }.build()
        it.proceed(newRequest)
    }

    private fun getLogInterceptor(): Interceptor {
        val logLevel = when (BuildConfig.DEBUG) {
            true -> HttpLoggingInterceptor.Level.BODY
            else -> HttpLoggingInterceptor.Level.NONE
        }
        return HttpLoggingInterceptor().apply { level = logLevel }
    }
}
