plugins {
    id("com.android.library")
    kotlin("android")
    `maven-publish`
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