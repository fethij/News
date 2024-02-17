plugins {
    alias(libs.plugins.news.android.library)
    alias(libs.plugins.news.android.library.compose)
    alias(libs.plugins.news.android.feature)
}

android {
    namespace = "com.example.news.feature.article"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.core.model)

    implementation(libs.hilt.android)
    implementation(libs.kotlinx.coroutines.android)
}