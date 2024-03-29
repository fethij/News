import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.example.news.buildlogic"

// Build-logic plugins targeting JDK 17, This matches the JDK used to build the project,
// and is not related to what is running on device.
java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplicationCompose") {
            id = "news.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidApplication") {
            id = "news.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidLibraryCompose") {
            id = "news.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidLibrary") {
            id = "news.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidFeature") {
            id = "news.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }

        register("androidHilt") {
            id = "news.android.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }

        register("androidFlavors") {
            id = "news.android.application.flavors"
            implementationClass = "AndroidApplicationFlavorsConventionPlugin"
        }

        register("jvmLibrary") {
            id = "news.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
    }
}
