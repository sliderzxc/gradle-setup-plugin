package com.sliderzxc.gradle.test

import kotlinx.validation.ApiValidationExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

fun Project.setupBinaryCompatibilityValidator() {
    if (Compilations.isGenericEnabled) {
        plugins.apply("org.jetbrains.kotlinx.binary-compatibility-validator")
        applyBinaryCompatibilityValidatorConfig()
    }
}

private fun Project.applyBinaryCompatibilityValidatorConfig() {
    val config = getDefaults<BinaryCompatibilityValidatorConfig>() ?: return

    extensions.configure<ApiValidationExtension> {
        nonPublicMarkers += config.nonPublicMarkers
    }
}
