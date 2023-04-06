package ru.testtobyte.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import ru.testtobyte.data.news.Article
import ru.testtobyte.data.topnews.Source

class NewsViewmodel(application: Application) : AndroidViewModel(application) {

    private var repository: NewsRepository

    init {
    repository = NewsRepository()

    }
    suspend fun checkTotalResults(query: String):Int{

        return repository.checkTotalResult(query)
    }

    suspend fun getNewsEverything(query: String):List<Article>{

        return repository.getNewsEverything(query)

    }

    suspend fun getTopNews():List<Source>{

        return repository.getTopNews()
}
}