// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    var kotlin_version = "1.5.10"
    repositories {
        google()
        jcenter()
        mavenCentral()
//        maven {
//            url = uri("https://plugins.gradle.org/m2/")
//        }
    }
    dependencies {
        classpath ("com.android.tools.build:gradle:7.0.1")
        classpath ("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlin_version")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt_version}")
        classpath ("com.google.protobuf:protobuf-gradle-plugin:${Versions.grpc_gradle_version}")
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.nav_version}")

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        mavenCentral()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
}

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}