plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.konrados.testconstraintlayout"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        applicationId = "com.konrados.testconstraintlayout"
        minSdk = 24
        targetSdk = 36
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
    android{
        buildFeatures {
            viewBinding = true
        }
    }
}

dependencies {
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation("com.squareup.retrofit2:converter-scalars:2.11.0")

    // Retrofit (HTTP client)
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
// JSON konwerter (Gson)
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")
// OkHttp + logging (do debugowania requestów)
    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")
}