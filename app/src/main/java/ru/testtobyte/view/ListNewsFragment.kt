package ru.testtobyte.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.testtobyte.adapters.NewsAdapter
import ru.testtobyte.data.NewsViewmodel
import ru.testtobyte.data.news.Article
import ru.testtobyte.data.newsServiceApi
import ru.testtobyte.databinding.FragmentListNewsBinding


class ListNewsFragment : Fragment() {
    private lateinit var binding: FragmentListNewsBinding

    private lateinit var viewModelNews: NewsViewmodel
    private var adapter = NewsAdapter()





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListNewsBinding.inflate(inflater)
        viewModelNews = ViewModelProvider(this).get(NewsViewmodel::class.java)
initAdapter()
getAllNews()
getNews()
initProgress()


        return binding.root
    }
    private fun filterlist(list:List<Article>):List<Article>{

    return  list.filter {
            it.author !=null
        }

    }


    private fun getAllNews(){
        lifecycleScope.launch {
            viewModelNews.getNewsEverything("news")
            viewModelNews.listEveryNews.observe(viewLifecycleOwner){listNews->
                adapter.submitList(filterlist(listNews))
                disableProgress()

            }
        }

       }






    private fun getNews(){
        val searchView = binding.searchView
        searchView.setOnQueryTextListener(object : OnQueryTextListener,
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(text: String): Boolean {
                    checkTotalResults(text)
               lifecycleScope.launch {
                   viewModelNews.getNewsEverything(text)
                viewModelNews.listEveryNews.observe(viewLifecycleOwner){listNews->
                adapter.submitList(filterlist(listNews))
}

               }


                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }


        })


    }

    private fun checkTotalResults(query: String){
        val notFound = binding.notfound
        notFound.visibility = View.GONE
        initProgress()

        CoroutineScope(Dispatchers.IO).launch {
            val totalResult = viewModelNews.checkTotalResults(query)
            activity?.runOnUiThread{
            if (totalResult == 0) {
                disableProgress()
                notFound.visibility = View.VISIBLE
            }else{
                disableProgress()

            }
        }
        }


    }
    private fun initProgress(){
        val progress = binding.progress
        progress.visibility = View.VISIBLE
    }
    private fun disableProgress(){
        val progress = binding.progress
        progress.visibility = View.GONE
    }



    private fun initAdapter(){
        val recycler = binding.rvNews
        recycler.adapter = adapter
        recycler.layoutManager = LinearLayoutManager(requireContext())
    }


}