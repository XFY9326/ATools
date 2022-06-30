plugins {
    id("com.android.library")
    kotlin("android")
    `maven-publish`
}

android {
    namespace = "io.github.xfy9326.atools.coroutines"
}

publishToJitPack("atools-coroutines")

dependencies {
    // Kotlin Coroutines
    implementation(libs.kotlinx.coroutines.android)
}
