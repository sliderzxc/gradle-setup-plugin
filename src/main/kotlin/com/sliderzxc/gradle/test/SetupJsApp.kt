package com.sliderzxc.gradle.test

import com.sliderzxc.gradle.test.disableCompilationsOfNeeded
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.jetbrains.kotlin.gradle.dsl.KotlinJsProjectExtension

fun Project.setupJsApp() {
    extensions.configure<KotlinJsProjectExtension> {
        js(IR) {
            browser()
            binaries.executable()
        }

        disableCompilationsOfNeeded()
    }
}
