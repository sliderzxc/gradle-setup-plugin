package com.sliderzxc.gradle.publishing.javadoc

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.create

/**
 * Ensures the existence of the Javadoc JAR task in the project. If the task already exists, it is returned.
 * Otherwise, a new Javadoc JAR task is created and returned.
 *
 * @return The Javadoc JAR task.
 */
internal fun Project.ensureJavadocJarTask(): Task {
    return tasks.findByName(JAVADOC_JAR_TASK_NAME) ?: createJavadocJarTask()
}

/**
 * Creates a new Javadoc JAR task in the project.
 *
 * @return The newly created Javadoc JAR task.
 */
private fun Project.createJavadocJarTask(): Task {
    return tasks.create<Jar>(JAVADOC_JAR_TASK_NAME).apply {
        archiveClassifier.set("javadoc")
    }
}

/**
 * The name of the Javadoc JAR task.
 */
private const val JAVADOC_JAR_TASK_NAME = "javadocJar"