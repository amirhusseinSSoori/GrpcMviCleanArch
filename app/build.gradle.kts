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
        artifact = Dependencies.protobuf_protoc
    }
    plugins {
        id("grpc") {
            artifact = (Dependencies.gen_grpc_java)
        }
    }
    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                create("java") { option("lite") }
            }
            task.plugins {
                create("grpc") { option("lite") }
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
    implementation(Dependencies.grpc_stub)
    implementation(Dependencies.grpc_protobuf_lite)
    compileOnly(Dependencies.annotations_api)


    //----------------- navigationComponent -----------------
    implementation(Dependencies.navigation_fragment_ktx)
    implementation(Dependencies.navigation_ui_ktx)


}