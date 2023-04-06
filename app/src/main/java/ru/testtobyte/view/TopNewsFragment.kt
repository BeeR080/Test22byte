package ru.testtobyte.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import ru.testtobyte.R
import ru.testtobyte.adapters.NewsAdapter
import ru.testtobyte.adapters.TopNewsAdapter
import ru.testtobyte.data.NewsViewmodel
import ru.testtobyte.databinding.FragmentTopNewsBinding


class TopNewsFragment : Fragment() {
    private lateinit var binding:FragmentTopNewsBinding

    private lateinit var viewModelNews: NewsViewmodel
    private var adapter = TopNewsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTopNewsBinding.inflate(inflater)

        viewModelNews = ViewModelProvider(this).get(NewsViewmodel::class.java)

        initAdapter()
        getTopNews()
        initProgress()

        return binding.root
    }

    private fun getTopNews(){

        lifecycleScope.launch{
            val list = viewModelNews.getTopNews()

            adapter.submitList(list)
            disableProgress()
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