// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.2.2" apply false
    id("com.android.library") version "7.2.2" apply false
    kotlin("android") version "1.7.0" apply false
    kotlin("jvm") version "1.7.0" apply false
}

subprojects {
    afterEvaluate {
        if (plugins.hasPlugin("com.android.library")) {
            extensions.findByType<com.android.build.gradle.LibraryExtension>()?.apply {
                compileSdk = SDKVersion.compileSDK
                defaultConfig {
                    minSdk = SDKVersion.minSDK
                    targetSdk = SDKVersion.targetSDK
                }

                buildTypes {
                    release {
                        isMinifyEnabled = false
                        proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
                    }
                }

                publishing {
                    singleVariant("release") {
                        withSourcesJar()
                    }
                }

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_11
                    targetCompatibility = JavaVersion.VERSION_11
                }
            }

            tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
                kotlinOptions {
                    jvmTarget = JavaVersion.VERSION_11.toString()
                }
            }

            dependencies {
                add("testImplementation", libs.junit)
                add("androidTestImplementation", libs.androidx.junit)
            }
        }
    }
}


tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}