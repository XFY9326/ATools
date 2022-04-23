plugins {
    id("com.android.library")
    kotlin("android")
    `maven-publish`
}

publishToJitPack("atools-livedata")

dependencies {
    // LiveData
    implementation(libs.androidx.livedata)

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}