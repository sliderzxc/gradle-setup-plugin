package com.sliderzxc.gradle.publishing.platforms.multiplatform

import com.sliderzxc.gradle.publishing.config.PublishingConfig
import com.sliderzxc.gradle.publishing.javadoc.ensureJavadocJarTask
import com.sliderzxc.gradle.publishing.platforms.multiplatform.pom.setupPublishingPom
import com.sliderzxc.gradle.publishing.platforms.multiplatform.repository.setupPublishingRepository
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.publish.maven.tasks.AbstractPublishToMaven
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.gradle.plugins.signing.Sign
import org.gradle.plugins.signing.SigningExtension

fun Project.setupMultiplatformPublishing(config: PublishingConfig) {
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