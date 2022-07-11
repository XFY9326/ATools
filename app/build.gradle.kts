plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = SDKVersion.compileSDK
    namespace = "io.github.xfy9326.atools.demo"

    defaultConfig {
        applicationId = "io.github.xfy9326.atools.demo"
        minSdk = SDKVersion.minSDK
        targetSdk = SDKVersion.targetSDK
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }
}

dependencies {
    implementation(project(":core"))
    implementation(project(":io"))
    implementation(project(":ui"))
    implementation(project(":compose"))

    implementation(project(":crash"))
    implementation(project(":livedata"))
    implementation(project(":coroutines"))
    implementation(project(":datastore-preference"))
    implementation(project(":datastore-preference-adapter"))
    implementation(project(":io-serialization-json"))

    // AndroidX
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}