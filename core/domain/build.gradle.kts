plugins {
    alias(libs.plugins.news.android.library)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.news.core.domain"
}

dependencies {
    implementation(projects.core.common)
    implementation(projects.core.data)
    implementation(projects.core.model)

    implementation(libs.hilt.android)
    implementation(libs.kotlinx.coroutines.android)

    ksp(libs.hilt.compiler)

    testImplementation(projects.core.testing)
}