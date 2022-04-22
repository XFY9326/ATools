plugins {
    id("com.android.library")
    kotlin("android")
    `maven-publish`
}

android {
    compileSdk = SDKVersion.compileSDK

    defaultConfig {
        minSdk = SDKVersion.minSDK
        targetSdk = SDKVersion.targetSDK

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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

publishing {
    publications {
        create<MavenPublication>("JitPack") {
            groupId = "io.github.xfy9326.atools"
            artifactId = "atools-livedata"
            version = currentGitCommitTag ?: "$gitCommitShortId-SNAPSHOT"

            afterEvaluate {
                from(components["release"])
            }
        }
    }
}

dependencies {
    // LiveData
    implementation(libs.androidx.livedata)

    // Test
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
}