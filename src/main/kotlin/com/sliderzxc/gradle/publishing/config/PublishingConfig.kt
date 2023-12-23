package com.sliderzxc.gradle.publishing.config

/**
 * Data class representing the combined configuration for publishing a library.
 *
 * @property libraryConfig The configuration for the library.
 * @property developerConfig The configuration for the developer.
 */
data class PublishingConfig(
    val libraryConfig: LibraryConfig,
    val developerConfig: DeveloperConfig
)