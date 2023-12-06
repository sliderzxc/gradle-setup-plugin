package com.sliderzxc.gradle.multiplatform.bundle

import com.sliderzxc.gradle.multiplatform.SourceSetBundle
import org.gradle.api.NamedDomainObjectContainer
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadOnlyProperty

fun NamedDomainObjectContainer<out KotlinSourceSet>.bundle(name: String): SourceSetBundle =
    SourceSetBundle(
        main = maybeCreate("${name}Main"),
        test = maybeCreate(if (name == "android") "${name}UnitTest" else "${name}Test"),
    )

fun NamedDomainObjectContainer<out KotlinSourceSet>.bundle(): PropertyDelegateProvider<Any?, ReadOnlyProperty<Any?, SourceSetBundle>> =
    PropertyDelegateProvider { _, property ->
        val bundle = bundle(property.name)
        ReadOnlyProperty { _, _ -> bundle }
    }