package com.example.news.core.datastore.di

import com.example.news.core.common.Dispatcher
import com.example.news.core.common.NewsDispatchers
import com.example.news.core.datastore.NewsDataSource
import com.example.news.core.datastore.assets.ArticlesDataSource
import com.example.news.core.datastore.assets.AssetManager
import com.example.news.core.datastore.assets.FakeAssetManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatastoreModule::class],
)
object FakeDatastoreModule {

    @Provides
    @Singleton
    fun provideAssetManager(): AssetManager = FakeAssetManagerImpl()

    @Provides
    @Singleton
    fun providesJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun providesNewsDataSource(
        @Dispatcher(NewsDispatchers.IO) ioDispatcher: CoroutineDispatcher,
        json: Json,
        assets: AssetManager,
    ): NewsDataSource = ArticlesDataSource(ioDispatcher, json, assets)
}