package com.sliderzxc.gradle.publishing.platforms.multiplatform.repository

import com.sliderzxc.gradle.publishing.config.PublishingConfig
import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.tasks.AbstractPublishToMaven
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.withType
import org.gradle.plugins.signing.Sign
import org.gradle.plugins.signing.SigningExtension

internal fun Project.setupPublishingRepository(config: PublishingConfig) {
    val signingTasks = tasks.withType<Sign>()

    tasks.withType<AbstractPublishToMaven>().configureEach {
        dependsOn(signingTasks)
    }

    extensions.configure<PublishingExtension> {
        extensions.configure<SigningExtension> {
            useInMemoryPgpKeys(
                config.libraryConfig.signingKey,
                config.libraryConfig.signingPassword
            )
            sign(publications)
        }

        repositories {
            maven {
                val isSnapshot = version.toString().endsWith("SNAPSHOT")
                url = uri(
                    if (isSnapshot) config.libraryConfig.snapshotRepository
                    else config.libraryConfig.releaseRepository
                )

                credentials {
                    username = config.developerConfig.username
                    password = config.developerConfig.password
                }
            }
        }
    }
}