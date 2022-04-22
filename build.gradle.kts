// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.1.3" apply false
    id("com.android.library") version "7.1.3" apply false
    kotlin("android") version "1.6.21" apply false
}

group = "io.github.xfy9326"

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}