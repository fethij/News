package com.example.news.core

import com.example.news.core.common.result.Result
import com.example.news.core.data.repository.ArticleRepository
import com.example.news.core.datastore.assets.ArticlesDataSource
import com.example.news.core.datastore.model.AssetArticle
import com.example.news.core.datastore.model.asArticle
import com.example.news.core.model.Article
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject
import kotlin.test.assertEquals


@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
@HiltAndroidTest
class ArticleRepositoryTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var articlesDataSource: ArticlesDataSource

    @Inject
    lateinit var articleRepository: ArticleRepository

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun `articlesRepository is backed by articlesDataSource`() = runTest {
        val repositoryResult: List<Article> =
            (articleRepository.getArticles() as Result.Success).data
        val dataSourceResult: List<Article> =
            (articlesDataSource.getArticles() as Result.Success).data.map(AssetArticle::asArticle)
        assertEquals(repositoryResult, dataSourceResult)
    }
}