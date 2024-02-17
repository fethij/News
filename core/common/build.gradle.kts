plugins {
    alias(libs.plugins.news.android.library)
    alias(libs.plugins.news.android.hilt)
}

android {
    namespace = "com.example.news.core.common"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)

    testImplementation(projects.core.testing)
}
