object Dependencies {

    //---------------------main--------------------
    val kotlin_sblib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    val kotlin_corKtx = "androidx.core:core-ktx:${Versions.corKtx}"
    val appcompat = "androidx.appcompat:appcompat:${Versions.appcompat}"
    val material = "com.google.android.material:material:${Versions.material}"
    val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"

    //---------------------hilt--------------------
    val hilt_android = "com.google.dagger:hilt-android:${Versions.hilt_version}"
    val hilt_compiler = "com.google.dagger:hilt-compiler:${Versions.hilt_version}"
    val hilt_lifecycl = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hilt_lifecycle_version}"

    //---------------------test--------------------
    val junit = "junit:junit:${Versions.junit_version}"
    val juiTest = "androidx.test.ext:junit:${Versions.junit_test_version}"
    val espresso_core = "androidx.test.espresso:espresso-core:${Versions.expresso_test_version}"



    //------------------------- navigationComponent ----------------------------------
    val navigation_fragment_ktx = "androidx.navigation:navigation-fragment-ktx:${Versions.nav_version}"
    val navigation_ui_ktx = "androidx.navigation:navigation-ui-ktx:${Versions.nav_version}"


    //---------------------coroutines--------------------
    val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines_version}"
    val coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.corotines_core}"





    //---------------------lifecycle--------------------
    val lifecycle_viewmode_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Lifecycle_ViewModel}"
    val LifeCycleRuntime = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.Lifecycle_ViewModel}"

    //---------------------grpc--------------------
    val grpc_okhttp = "io.grpc:grpc-okhttp:${Versions.grpc_Okhttp}"
    val grpc_stub = "io.grpc:grpc-stub:${Versions.grpc_stub}"
    val grpc_protobuf_lite = "io.grpc:grpc-protobuf-lite:${Versions.protobuf_lite}"
    val annotations_api = "org.apache.tomcat:annotations-api:${Versions.annotations_api}"



    //--------- plugin -------------
    val gen_grpc_java = "io.grpc:protoc-gen-grpc-java:${Versions.grpc_stub}"
    val protobuf_protoc = "com.google.protobuf:protoc:${Versions.proto}"

}