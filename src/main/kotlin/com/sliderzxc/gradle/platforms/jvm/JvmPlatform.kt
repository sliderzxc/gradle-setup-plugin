package com.sliderzxc.gradle.platforms.jvm

import com.sliderzxc.gradle.multiplatform.SourceSetBundle
import org.gradle.api.NamedDomainObjectContainer
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

fun NamedDomainObjectContainer<out KotlinSourceSet>.platformJvm(): SourceSetBundle {
    return SourceSetBundle(
        main = maybeCreate("main"),
        test = maybeCreate("test")
    )
}