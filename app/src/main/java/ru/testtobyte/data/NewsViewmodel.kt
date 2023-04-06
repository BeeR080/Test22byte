package ru.testtobyte.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.testtobyte.data.news.Article
import ru.testtobyte.data.topnews.Source

class NewsViewmodel() : ViewModel() {

    private var repository: NewsRepository = NewsRepository()
    val listTopNews: MutableLiveData<List<Source>> = MutableLiveData()
    val listEveryNews: MutableLiveData<List<Article>> = MutableLiveData()

    suspend fun checkTotalResults(query: String):Int {


        return repository.checkTotalResult(query)
    }

    suspend fun getNewsEverything(query: String){
        viewModelScope.launch {
            val response = repository.getNewsEverything(query)
            listEveryNews.value = response
        }



    }

    suspend fun getTopNews() {
    viewModelScope.launch {
    val response = repository.getTopNews()
        listTopNews.value = response
    }
}
}