plugins {
    id("com.android.library")
    kotlin("android")
    `maven-publish`
}

android {
    namespace = "io.github.xfy9326.atools.datastore.preference"
}

publishToJitPack("atools-datastore-preference")

dependencies {
    api(project(":core"))

    // Kotlin Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // DataStore
    implementation(libs.androidx.datastore.preferences)
}
