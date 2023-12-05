
package com.sliderzxc.gradle.platforms

import com.sliderzxc.gradle.multiplatform.SourceSetBundle
import org.gradle.api.NamedDomainObjectContainer
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

fun NamedDomainObjectContainer<out KotlinSourceSet>.platform(name: String): SourceSetBundle {
    return SourceSetBundle(
        main = maybeCreate("${name}Main"),
        test = maybeCreate(if (name == "android") "${name}UnitTest" else "${name}Test"),
    )
}