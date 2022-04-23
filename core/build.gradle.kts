plugins {
    id("com.android.library")
    kotlin("android")
    `maven-publish`
}

publishToJitPack("atools-core")

dependencies {
    // App StartUp
    implementation(libs.androidx.startup)

    // Android Dependencies
    api(libs.androidx.core)

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}