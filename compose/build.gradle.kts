plugins {
    id("com.android.library")
    kotlin("android")
    `maven-publish`
}

android {
    namespace = "io.github.xfy9326.atools.compose"

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.2.0"
    }
}

publishToJitPack("atools-compose")

dependencies {
    // Compose
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.preview)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material)

    // Debug
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.compose.ui.manifest)
    debugImplementation(libs.androidx.customview.poolingcontainer)
}
