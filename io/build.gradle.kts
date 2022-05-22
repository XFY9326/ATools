plugins {
    id("com.android.library")
    kotlin("android")
    `maven-publish`
}

android {
    namespace = "io.github.xfy9326.atools.io"
}

publishToJitPack("atools-io")

dependencies {
    api(project(":core"))

    // Okio
    api(libs.okio)

    // Kotlin Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}
