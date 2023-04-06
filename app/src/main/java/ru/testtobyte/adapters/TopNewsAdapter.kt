package ru.testtobyte.adapters

import kotlinx.android.synthetic.main.topnews_list.view.*
import ru.testtobyte.data.topnews.Source
import ru.testtobyte.utils.DiffUtilsTopnewsItemCallback
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

import ru.testtobyte.R



class TopNewsAdapter:ListAdapter<Source, TopNewsAdapter.NewsViewHolder>(DiffUtilsTopnewsItemCallback()) {



     class NewsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

        val name = itemView.tv_name_top
        val desc = itemView.tv_description_top
        val url = itemView.tv_url_top


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.topnews_list,parent,false)

        return NewsViewHolder(view)

    }
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int){

        val currentlist = getItem(position)
        holder.name.text = currentlist.name
        holder.desc.text = currentlist.description
        holder.url.text = currentlist.url


    }

}