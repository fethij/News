package com.example.news.core.domain

import com.example.news.core.common.result.Result
import com.example.news.core.data.repository.ArticleRepository
import com.example.news.core.model.Article
import javax.inject.Inject

/**
 * A use case which returns all articles
 */
class GetArticlesUseCase @Inject constructor(
    private val articleRepository: ArticleRepository
) {
    suspend operator fun invoke(): List<Article> =
        when (val articles = articleRepository.getArticles()) {
            is Result.Success -> articles.data
            is Result.Error -> emptyList() /* Could be handled in viewModel as well */
        }
}