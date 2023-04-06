package ru.testtobyte.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.news_list.view.*
import ru.testtobyte.R
import ru.testtobyte.data.news.Article
import ru.testtobyte.utils.DiffUtilsNewsItemCallback

class NewsAdapter:ListAdapter<Article, NewsAdapter.NewsViewHolder>(DiffUtilsNewsItemCallback()) {

    val picasso = Picasso.get()


 class NewsViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

       val author = itemView.tv_author
       val title = itemView.tv_title
       val desc = itemView.tv_description
       val url = itemView.tv_url
       val image = itemView.image

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
val view = LayoutInflater
    .from(parent.context)
    .inflate(R.layout.news_list,parent,false)

        return NewsViewHolder(view)

    }
    override fun onBindViewHolder(holder: NewsViewHolder, position: Int){

        val currentlist = getItem(position)
        holder.author.text = "${currentlist.author}, "+"${currentlist.source.name} ,"+ currentlist.publishedAt
        holder.title.text = currentlist.title
        holder.desc.text = currentlist.description
        holder.url.text = currentlist.url
        picasso
            .load(currentlist.urlToImage)
            .error(R.drawable.ic_launcher_background)
            .into(holder.image)




    }

}