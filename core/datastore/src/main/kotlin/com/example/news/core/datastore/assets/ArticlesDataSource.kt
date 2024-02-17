package com.example.news.core.datastore.assets

import com.example.news.core.common.Dispatcher
import com.example.news.core.common.NewsDispatchers.IO
import com.example.news.core.common.result.Result
import com.example.news.core.common.result.Result.Error
import com.example.news.core.common.result.Result.Success
import com.example.news.core.datastore.NewsDataSource
import com.example.news.core.datastore.model.ArticleResponse
import com.example.news.core.datastore.model.AssetArticle
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import javax.inject.Inject

/**
 * [NewsDataSource] implementation backed by an asset file.
 */
class ArticlesDataSource @Inject constructor(
    @Dispatcher(IO) private val ioDispatcher: CoroutineDispatcher,
    private val json: Json,
    private val assets: AssetManager,
) : NewsDataSource {

    /**
     * Returns a list of [AssetArticle]s
     */
    override suspend fun getArticles(): Result<List<AssetArticle>> = withContext(ioDispatcher) {

        return@withContext try {
            Success(
                assets.open(ARTICLES_ASSET).bufferedReader()
                    .use {
                        json.decodeFromString<ArticleResponse>(it.readText()).articles
                    }
            )
        } catch (e: Exception) {
            Error(e)
        }

    }

    companion object {
        const val ARTICLES_ASSET = "api_response.json"
    }
}