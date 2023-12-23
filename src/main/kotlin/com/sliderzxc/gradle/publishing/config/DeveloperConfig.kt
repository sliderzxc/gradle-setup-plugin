package com.sliderzxc.gradle.publishing.config

/**
 * Data class representing the configuration for a developer.
 *
 * @property id The developer's ID.
 * @property name The developer's name.
 * @property email The developer's email.
 * @property username The developer's username.
 * @property password The developer's password.
 */
data class DeveloperConfig(
    val id: String,
    val name: String,
    val email: String,
    val username: String,
    val password: String
)