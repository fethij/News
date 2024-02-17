package com.example.news.core.datastore

import com.example.news.core.common.result.Result
import com.example.news.core.datastore.model.AssetArticle

/**
 * Interface representing calls to the News assets
 */
interface NewsDataSource {

    /**
     * Returns a list of [AssetArticle]s.
     */
    suspend fun getArticles(): Result<List<AssetArticle>>
}
