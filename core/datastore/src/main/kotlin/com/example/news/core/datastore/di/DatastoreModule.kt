package com.example.news.core.datastore.di

import android.content.Context
import com.example.news.core.common.Dispatcher
import com.example.news.core.common.NewsDispatchers.IO
import com.example.news.core.datastore.NewsDataSource
import com.example.news.core.datastore.assets.ArticlesDataSource
import com.example.news.core.datastore.assets.AssetManager
import com.example.news.core.datastore.assets.AssetManagerImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatastoreModule {

    @Provides
    @Singleton
    fun provideAssetManager(
        @ApplicationContext context: Context,
    ): AssetManager = AssetManagerImpl(context)

    @Provides
    @Singleton
    fun providesJson(): Json = Json {
        ignoreUnknownKeys = true
    }

    @Provides
    @Singleton
    fun providesNewsDataSource(
        @Dispatcher(IO) ioDispatcher: CoroutineDispatcher,
        json: Json,
        assets: AssetManager,
    ): NewsDataSource = ArticlesDataSource(ioDispatcher, json, assets)
}