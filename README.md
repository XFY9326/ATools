# AndroidTools

Optimising the programming experience on Android

## Requirements

- OpenJDK 11
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

## Modules

| Library | Description |
| ----- | ----- |
| [IO](io/README.md) | File and image related tools |
