package com.sliderzxc.gradle.publishing.platforms.android

import com.android.build.gradle.LibraryExtension
import com.sliderzxc.gradle.publishing.config.PublishingConfig
import com.sliderzxc.gradle.publishing.platforms.multiplatform.applyMavenPublishPlugin
import com.sliderzxc.gradle.publishing.platforms.multiplatform.applySigningPlugin
import com.sliderzxc.gradle.publishing.platforms.multiplatform.pom.setupPublishingPom
import com.sliderzxc.gradle.publishing.platforms.multiplatform.repository.setupPublishingRepository
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.get
import org.gradle.kotlin.dsl.register

/**
 * Configures the publishing settings for an Android library using the Maven Publish plugin.
 *
 * @param config The publishing configuration.
 */
internal fun Project.setupAndroidLibraryPublishing(config: PublishingConfig) {
    applyMavenPublishPlugin()
    applySigningPlugin()

    extensions.configure<LibraryExtension> {
        publishing {
            singleVariant("release") {
                withSourcesJar()
                withJavadocJar()
            }
        }
    }

    extensions.configure<PublishingExtension> {
        publications {
            register<MavenPublication>("release") {
                groupId = config.libraryConfig.group
                version = config.libraryConfig.version
                artifactId = project.name

                setupPublishingPom(config)

                afterEvaluate {
                    from(components["release"])
                }
            }
        }
    }

    setupPublishingRepository(config)
}