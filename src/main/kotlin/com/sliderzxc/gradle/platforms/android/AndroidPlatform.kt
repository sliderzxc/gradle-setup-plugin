package com.sliderzxc.gradle.platforms.android

import org.gradle.api.NamedDomainObjectContainer
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

fun NamedDomainObjectContainer<out KotlinSourceSet>.platformAndroid(): AndroidSourceSetBundle {
    return AndroidSourceSetBundle(
        main = maybeCreate("Main"),
        test = KotlinSourceTestSet(
            unit = maybeCreate("Unit"),
            ui = maybeCreate("Ui")
        )
    )
}