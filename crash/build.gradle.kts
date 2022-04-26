plugins {
    id("com.android.library")
    kotlin("android")
    `maven-publish`
}

publishToJitPack("atools-crash")

dependencies {
    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}