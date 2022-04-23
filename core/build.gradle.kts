plugins {
    id("com.android.library")
    kotlin("android")
    `maven-publish`
}

publishToJitPack("atools-core")

dependencies {
    // App StartUp
    implementation(libs.androidx.startup)

    // AndroidX
    api(libs.androidx.core)
    api(libs.androidx.annotation)

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}