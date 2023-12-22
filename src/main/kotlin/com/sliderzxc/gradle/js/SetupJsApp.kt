package com.sliderzxc.gradle.js

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinJsProjectExtension

/**
 * Sets up a Kotlin/JS application for the Gradle project.
 *
 * This function configures the Kotlin/JS project extension for the project, enabling the generation
 * of JavaScript artifacts for the application.
 */
fun Project.setupJsApp() {
    extensions.configure<KotlinJsProjectExtension> {
        js(IR) {
            browser()
            binaries.executable()
        }
    }
}
