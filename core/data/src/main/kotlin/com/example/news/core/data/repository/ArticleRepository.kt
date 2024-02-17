package com.example.news.core.data.repository

import com.example.news.core.common.result.Result
import com.example.news.core.model.Article

/**
 * Data layer interface for the articles
 */
interface ArticleRepository {
    /**
     * Gets the available articles
     */
    suspend fun getArticles(): Result<List<Article>>
}
