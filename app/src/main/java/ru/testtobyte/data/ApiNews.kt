package ru.testtobyte.data

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query


import ru.testtobyte.view.MainActivity.Companion.BASE_URL
import ru.testtobyte.data.news.DataNews
import ru.testtobyte.data.topnews.DataTopNews
import ru.testtobyte.view.MainActivity

interface ApiNews {


@GET("v2/everything")
 suspend fun getNews(
    @Query("q") q:String,
    @Query("apiKey") api_key:String = MainActivity.API_KEY
 ):Response<DataNews>

 @GET("v2/top-headlines/sources")
 suspend fun getTopNews(
     @Query("apiKey") api_key:String = MainActivity.API_KEY):Response<DataTopNews>

}

val interceptor = HttpLoggingInterceptor()
    .setLevel(HttpLoggingInterceptor.Level.BODY)
val client = OkHttpClient().newBuilder().addInterceptor(interceptor)

val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .client(client.build())
    .build()

val newsServiceApi = retrofit.create(ApiNews::class.java)