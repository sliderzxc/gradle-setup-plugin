package com.sliderzxc.gradle.publishing.platforms.multiplatform

import org.gradle.api.Project

fun Project.applyMavenPublishPlugin() { plugins.apply(PublishingPluginName.MavenPublish.id) }
fun Project.applySigningPlugin() { plugins.apply(PublishingPluginName.Signing.id) }

enum class PublishingPluginName(val id: String) {
    MavenPublish("maven-publish"),
    Signing("signing"),
}