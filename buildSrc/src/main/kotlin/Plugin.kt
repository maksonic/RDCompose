import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.kotlin
import org.gradle.kotlin.dsl.version
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec

/**
 * @Author maksonic on 23.05.2022
 */
fun PluginDependenciesSpec.androidApp(): PluginDependencySpec =
    id("com.android.application")

fun PluginDependenciesSpec.androidLibrary(): PluginDependencySpec =
    id("com.android.library")

fun PluginDependenciesSpec.kotlinAndroid(): PluginDependencySpec =
    kotlin("android")

fun PluginDependenciesSpec.kotlinGradlePlugin(): PluginDependencySpec =
    id("org.jetbrains.kotlin.android") version Config.kotlinVersion apply false

fun PluginDependenciesSpec.hilt(): PluginDependencySpec =
    id("dagger.hilt.android.plugin")

fun PluginDependenciesSpec.kapt(): PluginDependencySpec =
    id("kotlin-kapt")

fun PluginDependenciesSpec.kotlinSerializationPlugin(): PluginDependencySpec =
    kotlin("plugin.serialization") version Config.serializationVersion apply false

fun PluginDependenciesSpec.kotlinSerialization(): PluginDependencySpec =
    id("kotlinx-serialization")

fun PluginDependenciesSpec.parcelize(): PluginDependencySpec =
    id("kotlin-parcelize")

fun PluginDependenciesSpec.ksp(): PluginDependencySpec =
    id("com.google.devtools.ksp") version Config.kspVersion
