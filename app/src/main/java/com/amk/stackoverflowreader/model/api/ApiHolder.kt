package com.amk.stackoverflowreader.model.api

import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiHolder {

    val dataSource by lazy {
//        val logging = HttpLoggingInterceptor()
//        logging.setLevel(Level.BASIC) //можно также заменить на Level.BODY чтоб увидеть полное тело запроса и ответа
//
//        val client = OkHttpClient.Builder()
//            .addInterceptor(logging)
//            .build()
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder()
            .addInterceptor(interceptor).build()


        val gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .excludeFieldsWithoutExposeAnnotation()
            .create()

        val retrofit = Retrofit.Builder()
            .client(client)
            .baseUrl("https://api.stackexchange.com")
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))

            .build()


        retrofit.create(DataSource::class.java)
    }
}