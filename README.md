# AndroidTools

[![](https://jitpack.io/v/io.github.xfy9326/atools.svg)](https://jitpack.io/#io.github.xfy9326/atools)

Optimising the programming experience on Android

## Requirements

- OpenJDK 17
- Kotlin is recommended
- Android SDK Version >= 23 (Android 6.0)

## Attention

**All APIs are not guaranteed to be stable, please select the code snippet you need to use.**

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

| Library                                                                    | Description                                       |
|----------------------------------------------------------------------------|---------------------------------------------------|
| [Base](base/README.md)                                                     | Basic functions                                   |
| [Core](core/README.md)                                                     | Basic android functions and application context   |
| [IO](io/README.md)                                                         | File and image related tools                      |
| [UI](ui/README.md)                                                         | UI related tools                                  |
| [Compose](compose/README.md)                                               | Jetpack Compose related tools                     |
| [Crash](crash/README.md)                                                   | Crash logger                                      |
| [Coroutines](coroutines/README.md)                                         | More Coroutines, Flow and Mutex functions         |
| [LiveData](livedata/README.md)                                             | EventLiveData and NotifyLiveData                  |
| [DataStore-Preference](datastore-preference/README.md)                     | DataStore Preference tools                        |
| [DataStore-Preference-ViewModel](datastore-preference-viewmodel/README.md) | DataStore Preference tools for AndroidX ViewModel |
| [DataStore-Preference-Adapter](datastore-preference-adapter/README.md)     | DataStore adapter for AndroidX Preference         |
| [IO-Serialization-Json](io-serialization-json/README.md)                   | Extra function for kotlinx serialization json     |
