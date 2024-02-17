package com.example.news.feature.article

import com.example.news.core.domain.GetArticlesUseCase
import com.example.news.core.model.Article
import com.example.news.core.testing.repository.TestArticlesRepository
import com.example.news.core.testing.util.MainDispatcherRule
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals

class ArticleViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private val articleRepository = TestArticlesRepository()
    private val useCase = GetArticlesUseCase(articleRepository)
    private lateinit var viewModel: ArticleViewModel

    @Before
    fun setUp() {
        viewModel = ArticleViewModel(useCase)
    }

    @Test
    fun `state is initially Empty`() = runTest {
        assertEquals(
            ArticlesUiState.Loading,
            viewModel.uiState.value,
        )
    }

    @Test
    fun `state is articles when articles are available and not empty`() = runTest {
        articleRepository.addArticles(testArticles)
        viewModel = ArticleViewModel(useCase)
        val collectJob =
            launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }
        assertEquals(
            ArticlesUiState.Articles(testArticles),
            viewModel.uiState.value,
        )
        collectJob.cancel()
    }

    @Test
    fun `state is articles when articles are available and empty`() = runTest {
        articleRepository.addArticles(emptyList())
        viewModel = ArticleViewModel(useCase)
        val collectJob =
            launch(UnconfinedTestDispatcher()) { viewModel.uiState.collect() }
        assertEquals(
            ArticlesUiState.Empty,
            viewModel.uiState.value,
        )
        collectJob.cancel()
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