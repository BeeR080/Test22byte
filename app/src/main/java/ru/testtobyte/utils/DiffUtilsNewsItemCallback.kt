package ru.testtobyte.utils

import androidx.recyclerview.widget.DiffUtil
import ru.testtobyte.data.news.Article

class DiffUtilsNewsItemCallback: DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.source.id == newItem.source.id
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}