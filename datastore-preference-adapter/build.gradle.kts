plugins {
    id("com.android.library")
    kotlin("android")
    `maven-publish`
}

android {
    namespace = "io.github.xfy9326.atools.datastore.preference.adapter"
}

publishToJitPack("atools-datastore-preference-adapter")

dependencies {
    // Kotlin Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // DataStore
    implementation(libs.androidx.datastore.pref)

    // AndroidX
    implementation(libs.androidx.preference)
}
