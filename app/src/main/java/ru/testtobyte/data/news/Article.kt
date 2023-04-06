package ru.testtobyte.data.news

data class Article(
    val author: String?,
    val content: String,
    val description: String,
    val publishedAt: String,
    var source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)