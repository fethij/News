package com.example.news.core.data.repository

import com.example.news.core.common.result.Result
import com.example.news.core.common.result.Result.Error
import com.example.news.core.common.result.Result.Success
import com.example.news.core.datastore.NewsDataSource
import com.example.news.core.datastore.model.AssetArticle
import com.example.news.core.datastore.model.asArticle
import com.example.news.core.model.Article
import javax.inject.Inject

/**
 * Asset backed implementation of the [ArticleRepository].
 */
class ArticleRepositoryImpl @Inject constructor(
    private val newsDataSource: NewsDataSource
) : ArticleRepository {
    override suspend fun getArticles(): Result<List<Article>> {
        return when (val articles = newsDataSource.getArticles()) {
            is Success -> Success(articles.data.map(AssetArticle::asArticle))
            is Error -> Error(articles.exception)
        }
    }
}