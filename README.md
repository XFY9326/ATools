# AndroidIOTools
[![](https://jitpack.io/v/io.github.xfy9326/AndroidIOTools.svg)](https://jitpack.io/#io.github.xfy9326/AndroidIOTools)  
Optimising the IO programming experience on Android

## Requirements
- Kotlin is recommended
- Android SDK Version >= 19

## Install
Gradle
```kotlin
// Method 1 (build.gradle.kts)
allprojects {
    repositories {
        // ...
        maven { url = uri("https://jitpack.io") }
    }
}

// Method 2 (settings.gradle.kts)
dependencyResolutionManagement {
    // ..
    repositories {
        // ...
        maven { url = uri("https://jitpack.io") }
    }
}
```
```kotlin
dependencies {
    implementation("io.github.xfy9326:AndroidIOTools:<version>")
}
```

## Build Environment
- OpenJDK 11

## Dependencies
- AndroidX StartUp
- AndroidX Annotation
- AndroidX Core
- KotlinX Coroutines
- Okio