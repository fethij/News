package com.example.news.core.datastore.assets

import java.io.InputStream

fun interface AssetManager {

    /**
     * Opens an [InputStream] to the given [fileName].
     *
     * @param fileName The name of the file to open.
     */
    fun open(fileName: String): InputStream
}
