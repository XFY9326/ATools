@file:Suppress("UnstableApiUsage")

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "8.1.0" apply false
    id("com.android.library") version "8.1.0" apply false
    kotlin("android") version "1.9.0" apply false
    kotlin("jvm") version "1.9.0" apply false
}

subprojects {
    afterEvaluate {
        if (plugins.hasPlugin("com.android.library")) {
            extensions.findByType<com.android.build.gradle.LibraryExtension>()?.apply {
                compileSdk = SDKVersion.compileSDK
                defaultConfig {
                    minSdk = SDKVersion.minSDK
                }

                buildTypes {
                    release {
                        isMinifyEnabled = false
                        proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
                    }
                }

                publishing {
                    multipleVariants {
                        withSourcesJar()
                        withJavadocJar()
                    }
                }

                compileOptions {
                    sourceCompatibility = JavaVersion.VERSION_17
                    targetCompatibility = JavaVersion.VERSION_17
                }
            }

            tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
                kotlinOptions {
                    jvmTarget = JavaVersion.VERSION_17.toString()
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