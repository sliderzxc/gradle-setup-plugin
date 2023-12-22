package com.sliderzxc.gradle.android.config

/**
 * The `AndroidConfig` class represents the Android configuration for set up android library or
 * kmp library using android platform.
 *
 * @property minSdkVersion The minimum SDK version required to run the application.
 * @property compileSdkVersion The SDK version used by the compiler during project compilation.
 * @property targetSdkVersion The SDK version the application is adapted to run on.
 * @property namespace The namespace that can be used for package name uniqueness in the project.
 */
data class AndroidConfig(
    val minSdkVersion: Int,
    val compileSdkVersion: Int,
    val targetSdkVersion: Int,
    val namespace: String
)