plugins {
    id("com.android.library")
    kotlin("android")
    `maven-publish`
}

android {
    namespace = "io.github.xfy9326.atools.livedata"
}

publishToJitPack("atools-livedata")

dependencies {
    // LiveData
    implementation(libs.androidx.livedata)
}
