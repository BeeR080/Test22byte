package ru.testtobyte.utils

import androidx.recyclerview.widget.DiffUtil
import ru.testtobyte.data.topnews.Source

class DiffUtilsTopnewsItemCallback: DiffUtil.ItemCallback<Source>() {
    override fun areItemsTheSame(oldItem: Source, newItem: Source): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Source, newItem: Source): Boolean {
        return oldItem == newItem
    }


}