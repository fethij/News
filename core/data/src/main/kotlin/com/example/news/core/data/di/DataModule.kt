package com.example.news.core.data.di

import com.example.news.core.data.repository.ArticleRepository
import com.example.news.core.data.repository.ArticleRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    fun bindsArticleRepository(
        articleRepositoryImpl: ArticleRepositoryImpl,
    ): ArticleRepository
}
