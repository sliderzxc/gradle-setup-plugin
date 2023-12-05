package com.sliderzxc.gradle.multiplatform

import org.gradle.api.NamedDomainObjectContainer
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet

interface MultiplatformSourceSets : NamedDomainObjectContainer<KotlinSourceSet> {

    val common: SourceSetBundle
    val androidSet: Set<SourceSetBundle>
    val jvmSet: Set<SourceSetBundle>
    val jsSet: Set<SourceSetBundle>
}