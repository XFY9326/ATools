plugins {
    id("com.android.library")
    kotlin("android")
    `maven-publish`
}

android {
    namespace = "io.github.xfy9326.atools.ui"
}

publishToJitPack("atools-ui")

dependencies {
    api(project(":core"))

    // AndroidX
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle.runtime)
    implementation(libs.androidx.lifecycle.common)
}
