package ru.testtobyte.data

import ru.testtobyte.data.news.Article
import ru.testtobyte.data.topnews.Source


class NewsRepository {

    suspend fun getNewsEverything(query:String):List<Article>{
      val query = newsServiceApi.getNews(query)
        val newsList = query.body()!!.articles
        return newsList
    }
    suspend fun checkTotalResult(query: String):Int{
        val querry = newsServiceApi.getNews(query)
        val newsTotal = querry.body()!!.totalResults
        return newsTotal
    }

    suspend fun getTopNews(): List<Source> {
        val query = newsServiceApi.getTopNews()
        val newsList = query.body()!!.sources
        return newsList
    }
}