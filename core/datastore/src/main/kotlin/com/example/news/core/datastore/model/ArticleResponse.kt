package com.example.news.core.datastore.model

import kotlinx.serialization.Serializable

@Serializable
data class ArticleResponse(
    val articles: List<AssetArticle>
)
