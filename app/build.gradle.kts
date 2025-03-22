plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.hilt)
}

android {
    namespace = "interactive.taskmanager"
    compileSdk = 35

    defaultConfig {
        applicationId = "interactive.taskmanager"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.constraintlayout.compose)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    /**
     * Navigation
     */
    implementation(libs.androidx.navigation.fragment)
    implementation(libs.androidx.navigation.ui)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.navigationCompose)

    /**
     * Room DB
     */
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
    implementation(libs.androidx.room.rxjava3)
    ksp(libs.androidx.room.compiler)

    /**
     * Hilt
     */
    implementation(libs.dagger.hilt)
    implementation(libs.androidx.hilt.workmanager)
    implementation(libs.androidx.hilt.navigationCompose)
    implementation(libs.androidx.hilt.navigationFragment)
    ksp(libs.androidx.hilt.compiler)
    ksp(libs.dagger.hilt.compiler)

    /**
     * SSP & SDP
     */
    implementation(libs.ssp.android)
    implementation(libs.sdp.android)

    /**
     * Icon
     */
    implementation(libs.androidx.material.icons.extended)

    /**
     * Serialization
     */
    implementation(libs.kotlinx.serialization.json)

    /**
     * Shimmer Effect
     */
    implementation(libs.compose.shimmer)

    /**
     * Preferences
     */
    implementation(libs.androidx.datastore.preferences)

    /**
     * WorkManager
     */
    implementation(libs.androidx.work.runtime.ktx) // if WorkManager is used

}