plugins {
    id("com.android.library")
    kotlin("android")
    `maven-publish`
}

publishToJitPack("atools-ui")

dependencies {
    api(project(":core"))

    // AndroidX
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.lifecycle.common)

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}