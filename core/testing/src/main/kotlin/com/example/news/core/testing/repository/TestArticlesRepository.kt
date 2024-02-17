package com.example.news.core.testing.repository

import com.example.news.core.common.result.Result
import com.example.news.core.data.repository.ArticleRepository
import com.example.news.core.model.Article

class TestArticlesRepository : ArticleRepository {

    private val articles = mutableListOf<Article>()

    override suspend fun getArticles(): Result<List<Article>> {
        return Result.Success(articles)
    }

    fun addArticles(list: List<Article>) {
        articles.addAll(list)
    }
}
