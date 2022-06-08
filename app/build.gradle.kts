import com.google.protobuf.gradle.*

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.protobuf")
    id("androidx.navigation.safeargs.kotlin")
}
protobuf{
    protoc {
        artifact = "com.google.protobuf:protoc:3.20.1"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.46.0"
        }
        id("grpckt") {
            artifact = ("io.grpc:protoc-gen-grpc-kotlin:1.3.0:jdk8@jar")
        }
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                create("java") { option("lite") }
                create("kotlin") { option("lite") }
            }
            task.plugins {
                create("grpc") { option("lite") }
                create("grpckt") { option("lite") }
            }
        }

    }

}

android {
    compileSdk= Versions.compile
    buildToolsVersion= Versions.buildTool


    defaultConfig {
        applicationId=Versions.applicationId
        minSdk = Versions.min
        targetSdk= Versions.target
        versionCode = 1
        versionName = "1.0"
        multiDexEnabled = true
        testInstrumentationRunner= "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    compileOptions {

        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }

}


dependencies {
    implementation(Dependencies.kotlin_sblib)
    implementation(Dependencies.kotlin_corKtx)
    implementation(Dependencies.appcompat)
    implementation(Dependencies.constraint)
    implementation(Dependencies.material)
    implementation(Dependencies.junit)
    androidTestImplementation(Dependencies.juiTest)
    androidTestImplementation(Dependencies.espresso_core)


    //----------------- hilt -----------------
    implementation(Dependencies.hilt_android)
    kapt(Dependencies.hilt_compiler)
    implementation(Dependencies.hilt_lifecycl)


    //----------------- coroutines -----------------
    implementation(Dependencies.coroutines)
    implementation(Dependencies.coroutines_core)
    implementation(Dependencies.lifecycle_viewmode_ktx)
    implementation(Dependencies.LifeCycleRuntime)


    //----------------- grpc -----------------
   implementation(Dependencies.grpc_okhttp)
//    implementation(Dependencies.grpc_stub)
//    implementation(Dependencies.grpc_protobuf_lite)
//    compileOnly(Dependencies.annotations_api)
    implementation("io.grpc:grpc-stub:1.46.0")
    api("io.grpc:grpc-stub:1.46.0")
    api("io.grpc:grpc-protobuf-lite:1.46.0")
    api("io.grpc:grpc-kotlin-stub:1.3.0")
//    api("io.grpc:protoc-gen-grpc-java:1.46.0")
    api("com.google.protobuf:protobuf-kotlin-lite:3.20.1")

    //----------------- navigationComponent -----------------
    implementation(Dependencies.navigation_fragment_ktx)
    implementation(Dependencies.navigation_ui_ktx)


}

//https://cloud.google.com/blog/products/application-development/use-grpc-with-kotlin
//https://github.com/grpc/grpc-kotlin