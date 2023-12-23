package com.sliderzxc.gradle.publishing.platforms.multiplatform

import org.gradle.api.Project

/**
 * Applies the Maven Publish plugin to the project.
 */
fun Project.applyMavenPublishPlugin() {
    plugins.apply(PublishingPluginName.MavenPublish.id)
}

/**
 * Applies the Signing plugin to the project.
 */
fun Project.applySigningPlugin() {
    plugins.apply(PublishingPluginName.Signing.id)
}

/**
 * Enum representing the names of publishing-related plugins.
 *
 * @property id The plugin ID.
 */
internal enum class PublishingPluginName(val id: String) {
    MavenPublish("maven-publish"),
    Signing("signing"),
}