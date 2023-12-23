package com.sliderzxc.gradle.publishing.platforms.multiplatform

import com.sliderzxc.gradle.publishing.config.PublishingConfig
import com.sliderzxc.gradle.publishing.javadoc.ensureJavadocJarTask
import com.sliderzxc.gradle.publishing.platforms.multiplatform.pom.setupPublishingPom
import com.sliderzxc.gradle.publishing.platforms.multiplatform.repository.setupPublishingRepository
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType

/**
 * Configures the publishing settings for a multiplatform project using the Maven Publish plugin.
 *
 * @param config The publishing configuration.
 */
internal fun Project.setupMultiplatformPublishing(config: PublishingConfig) {
    applyMavenPublishPlugin()
    applySigningPlugin()

    group = config.libraryConfig.group
    version = config.libraryConfig.version

    extensions.configure<PublishingExtension> {
        publications.withType<MavenPublication>().configureEach {
            artifact(project.ensureJavadocJarTask())

            setupPublishingPom(config)
        }
    }

    setupPublishingRepository(config)
}