plugins {
    id("com.android.library")
    kotlin("android")
    `maven-publish`
}

publishToJitPack("atools-datastore-preference")

dependencies {
    // Kotlin Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // DataStore
    implementation(libs.androidx.datastore.pref)

    // AndroidX
    implementation(libs.androidx.preference)

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}