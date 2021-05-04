import com.android.build.api.dsl.ApplicationBuildFeatures
import com.android.build.gradle.BaseExtension
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.google.protobuf.gradle.*
import org.gradle.api.JavaVersion
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompile
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

fun BaseAppModuleExtension.sdkTools() {
    compileSdkVersion(Versions.compile)
    buildToolsVersion(Versions.buildTool)

}

fun BaseAppModuleExtension.defaultConfig() = defaultConfig {
    applicationId(Versions.applicationId)
    minSdkVersion(Versions.min)
    targetSdkVersion(Versions.target)
    versionCode = (1)
    versionName = ("1.0")
    multiDexEnabled = true
    testInstrumentationRunner("androidx.test.runner.AndroidJUnitRunner")

}

fun BaseAppModuleExtension.buildTypes() =buildTypes{
    getByName("release") {
        isMinifyEnabled = false
        proguardFiles(
            getDefaultProguardFile("proguard-android-optimize.txt"),
            "proguard-rules.pro"
        )
    }
}

fun BaseAppModuleExtension.compileOptions() = compileOptions{
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}


fun BaseAppModuleExtension.viewBinding() = buildFeatures {
    viewBinding = true
}

fun ProtobufConfigurator.protoc() = protoc {
    artifact = Dependencies.protobuf_protoc
}

fun ProtobufConfigurator.plugins() = plugins {
    id("grpc") {
        artifact = (Dependencies.gen_grpc_java)
    }
}

fun ProtobufConfigurator.generateProtoTasks() = generateProtoTasks {
    all().forEach { task ->
        task.builtins {
            create("java") { option("lite") }
        }
        task.plugins {
            create("grpc") { option("lite") }
        }
    }

}

