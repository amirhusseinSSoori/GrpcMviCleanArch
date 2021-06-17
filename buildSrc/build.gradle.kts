plugins{
    `kotlin-dsl`
}
repositories {
    google()
    jcenter()

}
val kotlinVersion = "1.4.32"



dependencies {
    implementation("com.android.tools.build:gradle:4.2.1")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    implementation("com.google.protobuf:protobuf-gradle-plugin:0.8.15")
}
