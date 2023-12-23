package com.sliderzxc.gradle.multiplatform.bundle

import com.sliderzxc.gradle.multiplatform.SourceSetBundle
import org.gradle.api.NamedDomainObjectContainer
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import kotlin.properties.PropertyDelegateProvider
import kotlin.properties.ReadOnlyProperty

/**
 * Creates a SourceSetBundle for a specific name.
 *
 * @param name The name of the source set bundle.
 * @return The created SourceSetBundle.
 */
fun NamedDomainObjectContainer<out KotlinSourceSet>.bundle(name: String): SourceSetBundle {
    return SourceSetBundle(
        main = maybeCreate("${name}Main"),
        test = maybeCreate(if (name == "android") "${name}UnitTest" else "${name}Test"),
    )
}

/**
 * Provides a delegate for creating a SourceSetBundle without specifying a name.
 *
 * @return PropertyDelegateProvider for creating a SourceSetBundle.
 */
fun NamedDomainObjectContainer<out KotlinSourceSet>.bundle(): PropertyDelegateProvider<Any?, ReadOnlyProperty<Any?, SourceSetBundle>> {
    return PropertyDelegateProvider { _, property ->
        val bundle = bundle(property.name)
        ReadOnlyProperty { _, _ -> bundle }
    }
}