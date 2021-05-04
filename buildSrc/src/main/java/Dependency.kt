
import com.android.build.gradle.internal.dsl.BaseAppModuleExtension
import com.google.protobuf.gradle.protobuf
import org.gradle.api.Project
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.dependencies



fun Project.setUpProto() = protobuf{
    protoc()
    plugins()
    generateProtoTasks()
}

fun BaseAppModuleExtension.setUpAndroid() {
    sdkTools()
    defaultConfig()
    buildTypes()
    compileOptions()
    viewBinding()
}

fun Project.setUpDependencies() = dependencies {

    //------------------- Main ---------------------------------
    impl(Dependencies.kotlin_sblib)
    impl(Dependencies.kotlin_corKtx)
    impl(Dependencies.appcompat)
    impl(Dependencies.constraint)
    impl(Dependencies.material)
    impl(Dependencies.junit)
    androidTestImplementation(Dependencies.juiTest)
    androidTestImplementation(Dependencies.espresso_core)



    //----------------- hilt -----------------
    impl(Dependencies.hilt_android)
    kapt(Dependencies.hilt_compiler)
    impl(Dependencies.hilt_lifecycl)


    //----------------- coroutines -----------------
    impl(Dependencies.coroutines)
    impl(Dependencies.coroutines_core)
    impl(Dependencies.lifecycle_viewmode_ktx)
    impl(Dependencies.LifeCycleRuntime)


    //----------------- grpc -----------------
    impl(Dependencies.grpc_okhttp)
    impl(Dependencies.grpc_stub)
    impl(Dependencies.grpc_protobuf_lite)
    compileOnly(Dependencies.annotations_api)



    //----------------- navigationComponent -----------------
    impl(Dependencies.navigation_fragment_ktx)
    impl(Dependencies.navigation_ui_ktx)
}




fun DependencyHandler.`impl`(dependencyNotation: Any): Dependency? =
    add("implementation", dependencyNotation)
fun DependencyHandler.`kapt`(dependencyNotation: Any): Dependency? =
    add("kapt", dependencyNotation)

fun DependencyHandler.`compileOnly`(dependencyNotation: Any): Dependency? =
    add("compileOnly", dependencyNotation)

fun DependencyHandler.`androidTestImplementation`(dependencyNotation: Any): Dependency? =
    add("androidTestImplementation", dependencyNotation)



