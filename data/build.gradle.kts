//import org.jetbrains.kotlin.ir.backend.js.compile

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp.plugin)


}

android {
    namespace = "com.jcdesign.headhunter.mylibrary"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(project(":domain"))


    implementation(libs.kotlin.gson)
    implementation(libs.retrofit.gson)
    implementation(libs.retrofit2)
    implementation(libs.okhttp3)
    implementation(libs.androidx.annotation.jvm)

    implementation(libs.android.room)
    implementation(libs.android.room.ktx)

    implementation(libs.android.hilt)
    ksp(libs.android.room.compiler)



    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}