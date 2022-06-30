plugins {
    id("com.android.library")
    kotlin("android")
    `maven-publish`
}

android {
    namespace = "io.github.xfy9326.atools.core"
}

publishToJitPack("atools-core")

dependencies {
    api(project(":base"))

    // App StartUp
    implementation(libs.androidx.startup)

    // AndroidX
    api(libs.androidx.core)
    api(libs.androidx.annotation)
    api(libs.androidx.activity.ktx)
}
