import org.gradle.kotlin.dsl.kotlin
import org.gradle.plugin.use.PluginDependenciesSpec
import org.gradle.plugin.use.PluginDependencySpec


fun PluginDependenciesSpec.androidApp(): PluginDependencySpec =
    id("com.android.application")

fun PluginDependenciesSpec.kotlinKaptExt(): PluginDependencySpec =
    kotlin("kapt")
fun PluginDependenciesSpec.daggerHilt(): PluginDependencySpec =
    id("dagger.hilt.android.plugin")

fun PluginDependenciesSpec.kotlinAndroid(): PluginDependencySpec =
    kotlin("android")

fun PluginDependenciesSpec.navigationComponent(): PluginDependencySpec =
    id("androidx.navigation.safeargs.kotlin")


fun PluginDependenciesSpec.protobufGoogle(): PluginDependencySpec =
    id("com.google.protobuf")


