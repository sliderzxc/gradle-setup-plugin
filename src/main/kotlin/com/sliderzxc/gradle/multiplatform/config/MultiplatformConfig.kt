package com.sliderzxc.gradle.multiplatform.config

import com.sliderzxc.gradle.multiplatform.platforms.Platform

/**
 * Configuration data class for a multiplatform Gradle project.
 *
 * @param platforms The set of platforms supported by the project.
 */
data class MultiplatformConfig(
    val platforms: Set<Platform>
)