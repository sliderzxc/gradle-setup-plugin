package com.sliderzxc.gradle.publishing.config

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