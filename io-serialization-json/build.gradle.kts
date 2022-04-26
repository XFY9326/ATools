plugins {
    id("com.android.library")
    kotlin("android")
    `maven-publish`
}

publishToJitPack("atools-io-serialization-json")

dependencies {
    api(project(":io"))

    // Kotlin Serialization Json
    implementation(libs.kotlinx.serialization.json)

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}