package com.sliderzxc.gradle.platforms

import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinJsProjectExtension

internal fun Project.setupJsApp() {
    extensions.configure<KotlinJsProjectExtension> {
        js(IR) {
            browser()
            binaries.executable()
        }
    }
}
