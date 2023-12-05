package com.sliderzxc.gradle.platforms.android

import org.gradle.api.NamedDomainObjectContainer
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadOnlyProperty

private fun NamedDomainObjectContainer<out KotlinSourceSet>.android(): AndroidSourceSetBundle {
    return AndroidSourceSetBundle(
        main = maybeCreate("Main"),
        test = KotlinSourceTestSet(
            unit = maybeCreate("Unit"),
            ui = maybeCreate("Ui")
        )
    )
}

fun NamedDomainObjectContainer<out KotlinSourceSet>.platformAndroid(): PropertyDelegateProvider<Any?, ReadOnlyProperty<Any?, AndroidSourceSetBundle>> {
     return PropertyDelegateProvider { _, _ ->
         val platform = android()
         ReadOnlyProperty { _, _ -> platform }
     }
}


infix fun NamedDomainObjectContainer<out KotlinSourceSet>.by(a: AndroidSourceSetBundle) {
    this by a
}