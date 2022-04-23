# AndroidTools
[![](https://jitpack.io/v/io.github.xfy9326/atools.svg)](https://jitpack.io/#io.github.xfy9326/atools)  

Optimising the programming experience on Android

## Requirements

- OpenJDK 11
- Kotlin is recommended
- Android SDK Version >= 23 (Android 6.0)

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
| [Core](core/README.md) | Base functions and application context |
| [IO](io/README.md) | File and image related tools |
| [UI](ui/README.md) | UI related tools |
| [Coroutines](coroutines/README.md) | More Coroutines, Flow and Mutex functions |
| [LiveData](livedata/README.md) | EventLiveData and NotifyLiveData |
| [DataStore-Preference](datastore-preference/README.md) | DataStore and Preference tools |
