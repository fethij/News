package com.example.news.core.datastore.assets

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.InputStream
import javax.inject.Inject

/**
 * Implementation of the [AssetManager].
 */
class AssetManagerImpl @Inject constructor(
    @ApplicationContext private val context: Context,
) : AssetManager {
    override fun open(fileName: String): InputStream {
        return context.assets.open(fileName)
    }
}