plugins {
    alias(libs.plugins.news.android.library)
    alias(libs.plugins.news.android.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "com.example.news.core.datastore"

    buildFeatures {
        buildConfig = true
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.common)

    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)

    testImplementation(projects.core.testing)
}
