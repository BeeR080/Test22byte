package ru.testtobyte.data

import ru.testtobyte.data.news.Article
import ru.testtobyte.data.topnews.Source


class NewsRepository {

    suspend fun getNewsEverything(query:String):List<Article>{
      val query = newsServiceApi.getNews(query)
        if(query.isSuccessful) return query.body()!!.articles

            return emptyList()
    }

    suspend fun checkTotalResult(query: String):Int{
        val querry = newsServiceApi.getNews(query)
        val newsTotal = querry.body()!!.totalResults
        return newsTotal
    }

    suspend fun getTopNews(): List<Source> {
        val query = newsServiceApi.getTopNews()
        if(query.isSuccessful) return query.body()!!.sources

     return emptyList()
    }

}