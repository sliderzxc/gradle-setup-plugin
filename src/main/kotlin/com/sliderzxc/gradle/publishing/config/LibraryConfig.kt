package com.sliderzxc.gradle.publishing.config

/**
 * Data class representing the configuration for a library to be published.
 *
 * @property group The group ID of the library.
 * @property version The version of the library.
 * @property name The name of the library.
 * @property description The description of the library.
 * @property url The URL of the library.
 * @property scmUrl The SCM URL of the library.
 * @property releaseRepository The repository for releasing stable versions.
 * @property snapshotRepository The repository for releasing snapshot versions.
 * @property licenseName The name of the license.
 * @property licenseUrl The URL of the license.
 * @property signingKey The signing key for artifacts.
 * @property signingPassword The password for the signing key.
 */
data class LibraryConfig(
    val group: String,
    val version: String,
    val name: String,
    val description: String,
    val url: String,
    val scmUrl: String,
    val releaseRepository: String,
    val snapshotRepository: String,
    val licenseName: String,
    val licenseUrl: String,
    val signingKey: String,
    val signingPassword: String
)