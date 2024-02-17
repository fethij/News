package com.example.news.core.datastore.assets

import java.io.ByteArrayInputStream
import java.io.InputStream

const val title = "Fake Title"
const val description = "Fake Description"
const val type = "Fake Type"

class FakeAssetManagerImpl: AssetManager {
    private val articlesJson = """
            { "articles" : [
                {"type": "$type", "title": "$title", "description": "$description", "headerImageURL": "http://example.com/image1.jpg"},
                {"type": "Opinion", "title": "Mock Title 2", "description": "Mock Description 2", "headerImageURL": "http://example.com/image2.jpg"}
            ] }
            """
    override fun open(fileName: String): InputStream {
        return ByteArrayInputStream(articlesJson.toByteArray(Charsets.UTF_8))
    }
}