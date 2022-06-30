plugins {
    id("com.android.library")
    kotlin("android")
    `maven-publish`
}

android {
    namespace = "io.github.xfy9326.atools.io.serialization.json"
}

publishToJitPack("atools-io-serialization-json")

dependencies {
    api(project(":io"))

    // Kotlin Serialization Json
    implementation(libs.kotlinx.serialization.json)

    // Kotlin Coroutines
    implementation(libs.kotlinx.coroutines.android)
}
