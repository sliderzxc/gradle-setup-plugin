package com.sliderzxc.gradle.publishing.platforms.multiplatform

import com.sliderzxc.gradle.publishing.config.PublishingConfig
import com.sliderzxc.gradle.publishing.javadoc.ensureJavadocJarTask
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
            setupPublicationPom(config)
        }
    }
}

private fun MavenPublication.setupPublicationPom(config: PublishingConfig) {
    pom {
        name.set(config.libraryConfig.name)
        description.set(config.libraryConfig.description)
        url.set(config.libraryConfig.url)

        licenses {
            license {
                name.set(config.libraryConfig.licenseName)
                url.set(config.libraryConfig.licenseUrl)
            }
        }

        developers {
            developer {
                id.set(config.developerConfig.id)
                name.set(config.developerConfig.name)
                email.set(config.developerConfig.email)
            }
        }

        scm {
            url.set(config.libraryConfig.url)
            connection.set(config.libraryConfig.scmUrl)
            developerConnection.set(config.libraryConfig.scmUrl)
        }
    }
}

private fun Project.setupPublicationRepository(config: PublishingConfig) {
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