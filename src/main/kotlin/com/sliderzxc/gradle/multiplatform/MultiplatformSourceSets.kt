package com.sliderzxc.gradle.multiplatform

import com.sliderzxc.gradle.multiplatform.bundle.bundle
import org.gradle.api.NamedDomainObjectCollection
import org.gradle.api.NamedDomainObjectContainer
import org.jetbrains.kotlin.gradle.dsl.KotlinMultiplatformExtension
import org.jetbrains.kotlin.gradle.plugin.KotlinPlatformType
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet
import org.jetbrains.kotlin.gradle.plugin.KotlinTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import org.jetbrains.kotlin.konan.target.Family

/**
 * Extension function for setting up Kotlin multiplatform source sets.
 *
 * @param block Configuration block for multiplatform source sets.
 */
fun KotlinMultiplatformExtension.setupSourceSets(block: MultiplatformSourceSets.() -> Unit) {
    DefaultMultiplatformSourceSets(targets, sourceSets).block()
}

/**
 * Interface for managing and categorizing different source sets based on Kotlin targets.
 */
interface MultiplatformSourceSets : NamedDomainObjectContainer<KotlinSourceSet> {
    val common: SourceSetBundle
    val allSet: Set<SourceSetBundle>
    val javaSet: Set<SourceSetBundle>
    val nativeSet: Set<SourceSetBundle>
    val linuxSet: Set<SourceSetBundle>
    val darwinSet: Set<SourceSetBundle>
    val iosSet: Set<SourceSetBundle>
    val watchosSet: Set<SourceSetBundle>
    val tvosSet: Set<SourceSetBundle>
    val macosSet: Set<SourceSetBundle>
}

/**
 * Default implementation of [MultiplatformSourceSets].
 *
 * @property targets Named domain object collection for Kotlin targets.
 * @property sourceSets Named domain object container for Kotlin source sets.
 */
class DefaultMultiplatformSourceSets(
    private val targets: NamedDomainObjectCollection<KotlinTarget>,
    private val sourceSets: NamedDomainObjectContainer<KotlinSourceSet>,
) : MultiplatformSourceSets, NamedDomainObjectContainer<KotlinSourceSet> by sourceSets {

    override val common: SourceSetBundle by bundle()

    override val allSet: Set<SourceSetBundle> = targets.toSourceSetBundles()

    override val javaSet: Set<SourceSetBundle> = targets.filter {
        it.platformType in setOf(KotlinPlatformType.androidJvm, KotlinPlatformType.jvm)
    }.toSourceSetBundles()

    override val nativeSet: Set<SourceSetBundle> = nativeSourceSets()
    override val linuxSet: Set<SourceSetBundle> = nativeSourceSets(Family.LINUX)
    override val darwinSet: Set<SourceSetBundle> = nativeSourceSets(
        Family.IOS, Family.OSX, Family.WATCHOS, Family.TVOS
    )
    override val iosSet: Set<SourceSetBundle> = nativeSourceSets(Family.IOS)
    override val watchosSet: Set<SourceSetBundle> = nativeSourceSets(Family.WATCHOS)
    override val tvosSet: Set<SourceSetBundle> = nativeSourceSets(Family.TVOS)
    override val macosSet: Set<SourceSetBundle> = nativeSourceSets(Family.OSX)

    private fun nativeSourceSets(vararg families: Family = Family.values()): Set<SourceSetBundle> {
        return targets.filterIsInstance<KotlinNativeTarget>()
            .filter { it.konanTarget.family in families }
            .toSourceSetBundles()
    }

    private fun Iterable<KotlinTarget>.toSourceSetBundles(): Set<SourceSetBundle> {
        return filter { it.platformType != KotlinPlatformType.common }
            .map { it.getSourceSetBundle() }
            .toSet()
    }

    private fun KotlinTarget.getSourceSetBundle(): SourceSetBundle {
        return if (compilations.isEmpty()) {
            bundle(name)
        } else {
            SourceSetBundle(
                main = compilations.getByName("main").defaultSourceSet,
                test = compilations.getByName("test").defaultSourceSet,
            )
        }
    }
}