plugins {
    androidApp()
    kotlinAndroid()
    kotlinKaptExt()
    daggerHilt()
    protobufGoogle()
    navigationComponent()
}
setUpProto()
android {
    setUpAndroid()
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
setUpDependencies()