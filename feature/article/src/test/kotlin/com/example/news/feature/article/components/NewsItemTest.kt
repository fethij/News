package com.example.news.feature.article.components

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import com.example.news.core.model.Article
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class NewsItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val testArticle = Article(
        title = "title",
        description = "description",
        type = "type",
        headerImageURL = "headerImageURL"
    )

    @Before
    fun setUp() {
        composeTestRule.setContent {
            NewsItem(
                article = testArticle
            )
        }
    }

    @Test
    fun `news item properties are visible`() {
        composeTestRule.onNodeWithText(testArticle.title).assertExists()
        composeTestRule.onNodeWithText(testArticle.description).assertExists()
    }
}