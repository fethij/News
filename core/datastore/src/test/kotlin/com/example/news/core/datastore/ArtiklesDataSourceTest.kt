package com.example.news.core.datastore

import com.example.news.core.common.result.Result
import com.example.news.core.datastore.assets.ArticlesDataSource
import com.example.news.core.datastore.assets.description
import com.example.news.core.datastore.assets.title
import com.example.news.core.datastore.assets.type
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject


@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
@HiltAndroidTest
class ArticlesDataSourceTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var articlesDataSource: ArticlesDataSource

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun `getArticles returns a list of articles`() = runTest {
        val result = articlesDataSource.getArticles()
        assert((result as Result.Success).data.isNotEmpty())
    }

    @Test
    fun `getArticles returns Result Success`() = runTest {
        val result = articlesDataSource.getArticles()
        assert(result is Result.Success)
    }

    @Test
    fun `ArticleDataSource returns a list of articles`() = runTest {
        (articlesDataSource.getArticles() as Result.Success).data.apply {
            assertNotNull(this)
            assertEquals(2, size)
            assertEquals(description, this[0].description)
            assertEquals(title, this[0].title)
            assertEquals(type, this[0].type)
        }
    }
}