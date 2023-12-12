import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
    `java-gradle-plugin`
    id("maven-publish")
}

group = "com.sliderzxc.gradle"
version = "0.0.1"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "11"
    }
}

repositories {
    gradlePluginPortal()
    mavenCentral()
    google()
}

dependencies {
    compileOnly("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.20")
    compileOnly("com.android.tools.build:gradle:8.0.2")
    compileOnly("org.jetbrains.intellij.plugins:gradle-intellij-plugin:1.15.0")
    compileOnly("org.jetbrains.kotlinx:binary-compatibility-validator:0.13.2")
    compileOnly("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.23.3")

    implementation("org.redundent:kotlin-xml-builder:1.9.1")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.31")
    implementation("org.apache.servicemix.bundles:org.apache.servicemix.bundles.xpp3:1.1.4c_4")
    implementation("com.squareup.okhttp3:okhttp:4.11.0")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.0-RC")
}

gradlePlugin {
    plugins.create(project.name) {
        id = "com.sliderzxc.gradle.setup"
        implementationClass = "com.sliderzxc.gradle.GradleSetupPlugin"
    }
}
