package com.example.news.feature.article

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import com.example.news.core.model.Article
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class ArticleScreenTest {
    private val context get() = InstrumentationRegistry.getInstrumentation().targetContext

    @get:Rule
    val composeTestRule = createComposeRule()
    private var uiState: ArticlesUiState = ArticlesUiState.Empty

    @Test
    fun `articles screen when uiState is Empty`() {
        uiState = ArticlesUiState.Empty
        composeTestRule.setContent {
            ArticleScreen(uiState = uiState)
        }

        val articleScreen = context.getString(R.string.article_screen)
        val noNews = context.getString(R.string.no_news)

        composeTestRule.onNodeWithTag(articleScreen).assertExists()
        composeTestRule.onNodeWithText(noNews).assertExists()
    }

    @Test
    fun `articles screen when state is Loading`() {
        uiState = ArticlesUiState.Loading
        composeTestRule.setContent {
            ArticleScreen(uiState = uiState)
        }

        val articleScreen = context.getString(R.string.article_screen)
        val loading = context.getString(R.string.article_loading)

        composeTestRule.onNodeWithTag(articleScreen).assertExists()
        composeTestRule.onNodeWithTag(loading).assertExists()
    }

    @Test
    fun `articles screen when state is Articles`() {
        uiState = ArticlesUiState.Articles(testArticles)
        composeTestRule.setContent {
            ArticleScreen(uiState = uiState)
        }
        composeTestRule.waitForIdle()
        val articleScreen = context.getString(R.string.article_screen)
        val articlesList = context.getString(R.string.article_list)

        composeTestRule.onNodeWithTag(articleScreen).assertExists()
        composeTestRule.onNodeWithTag(articlesList).assertExists()
        composeTestRule.onNodeWithTag(articlesList).assertExists()
        composeTestRule.onNodeWithText(testArticles[0].type).assertExists()
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