package com.example.news.core.datastore.model

import com.example.news.core.model.Article
import kotlinx.serialization.Serializable

/**
 * Serializable representation of [Article]
 */
@Serializable
data class AssetArticle(
    val type: String,
    val title: String,
    val description: String,
    val headerImageURL: String
)

fun AssetArticle.asArticle() = Article(
    type = type,
    title = title,
    description = description,
    headerImageURL = headerImageURL
)

