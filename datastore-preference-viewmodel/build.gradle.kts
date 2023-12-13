plugins {
    id("com.android.library")
    kotlin("android")
    `maven-publish`
}

android {
    namespace = "io.github.xfy9326.atools.datastore.preference.viewmodel"
}

publishToJitPack("atools-datastore-viewmodel")

dependencies {
    api(project(":datastore-preference"))

    // Kotlin Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // DataStore
    implementation(libs.androidx.datastore.preferences)

    // AndroidX
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.lifecycle.common)
}