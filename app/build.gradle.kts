plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.dagger.hilt.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.baselineprofile)
    alias(libs.plugins.compose.compiler)

}

android {
    namespace = "ru.poly.kinopoisk"
    compileSdk = 35

    defaultConfig {
        applicationId = "ru.poly.kinopoisk"
        minSdk = 28
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.navigation.compose)

    implementation(libs.dagger.hilt.android)
    implementation(libs.androidx.profileinstaller)
    implementation(libs.androidx.foundation.layout.android)
    implementation(libs.androidx.material3.android)

    kapt(libs.dagger.hilt.compiler)

    implementation(project(":features:main_search"))
    implementation(project(":features:movie_detail"))
}