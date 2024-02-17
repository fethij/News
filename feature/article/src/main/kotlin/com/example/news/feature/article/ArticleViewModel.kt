package com.example.news.feature.article

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.core.domain.GetArticlesUseCase
import com.example.news.core.model.Article
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ArticleViewModel @Inject constructor(
    getArticles: GetArticlesUseCase,
) : ViewModel() {

    private var _uiState = MutableStateFlow<ArticlesUiState>(ArticlesUiState.Loading)
    val uiState: StateFlow<ArticlesUiState> = _uiState
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ArticlesUiState.Loading
        )

    init {
        viewModelScope.launch {
            val articles = getArticles()
            if (articles.isEmpty())
                _uiState.update { ArticlesUiState.Empty }
            else
                _uiState.update { ArticlesUiState.Articles(articles) }
        }
    }
}

sealed interface ArticlesUiState {
    data object Loading : ArticlesUiState

    data class Articles(
        val articles: List<Article>,
    ) : ArticlesUiState

    data object Empty : ArticlesUiState
}