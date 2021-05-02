plugins{
    `kotlin-dsl`
}
repositories {
    google()
    jcenter()

}
val kotlinVersion = "1.4.31"



dependencies {
    implementation("com.android.tools.build:gradle:4.1.3")
    implementation("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
}
