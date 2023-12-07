package com.sliderzxc.gradle.publishing.javadoc

import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.tasks.bundling.Jar
import org.gradle.kotlin.dsl.create

internal fun Project.ensureJavadocJarTask(): Task {
    return tasks.findByName(JAVADOC_JAR_TASK_NAME) ?: createJavadocJarTask()
}

private fun Project.createJavadocJarTask(): Task {
    return tasks.create<Jar>(JAVADOC_JAR_TASK_NAME).apply {
        archiveClassifier.set("javadoc")
    }
}

private const val JAVADOC_JAR_TASK_NAME = "javadocJar"