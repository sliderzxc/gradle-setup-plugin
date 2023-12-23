package com.sliderzxc.gradle.publishing.platforms.multiplatform.pom

import com.sliderzxc.gradle.publishing.config.PublishingConfig
import org.gradle.api.publish.maven.MavenPublication

/**
 * Configures the POM settings for a MavenPublication based on the provided PublishingConfig.
 *
 * @param config The publishing configuration.
 */
internal fun MavenPublication.setupPublishingPom(config: PublishingConfig) {
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