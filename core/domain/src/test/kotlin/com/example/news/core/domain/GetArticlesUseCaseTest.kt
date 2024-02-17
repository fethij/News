package com.example.news.core.domain

import com.example.news.core.model.Article
import com.example.news.core.testing.repository.TestArticlesRepository
import com.example.news.core.testing.util.MainDispatcherRule
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals


class GetArticlesUseCaseTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()
    private val articleRepository = TestArticlesRepository()
    val useCase = GetArticlesUseCase(articleRepository)

    @Test
    fun `GetArticlesUseCase is backed by ArticleRepository`() = runTest {
        val articles = useCase()
        articleRepository.addArticles(testArticles)
        assertEquals(testArticles, articles,)
        assertEquals(testArticles.size, articles.size)
    }
}

private val testArticles = listOf(
    Article(
        type = "1",
        title = "Title 1",
        description = "Description 1",
        headerImageURL = "https://example.com/1.jpg",
    ),
    Article(
        type = "2",
        title = "Title 2",
        description = "Description 2",
        headerImageURL = "https://example.com/2.jpg",
    ),
    Article(
        type = "3",
        title = "Title 3",
        description = "Description 3",
        headerImageURL = "https://example.com/3.jpg",
    )
)