plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("androidx.navigation.safeargs.kotlin")
    id("dagger.hilt.android.plugin")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
}

apply {
    from("../ktlint.gradle.kts")
}

android {
    compileSdkVersion(Version.COMPILE_SDK)
    buildToolsVersion(Version.BUILD_TOOL)
    defaultConfig {
        applicationId = "com.andrdoidlifelang.androidcleanarchitecturejetpack"
        minSdkVersion(Version.MIN_SDK)
        targetSdkVersion(Version.TARGET_SDK)
        versionCode = Version.VERSION_CODE
        versionName = Version.VERSION_NAME
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isDebuggable = true
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
    buildFeatures {
        dataBinding = true
    }
    androidExtensions {
        isExperimental = true
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(project(Module.CORE_PRESENTATION))
    implementation(project(Module.DOMAIN))
    implementation(project(Module.DATA))

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // Core
    implementation(Lib.KOTLIN_STDLIB)
    implementation(Lib.APP_COMPAT)
    implementation(Lib.CORE_KTX)
    implementation(Lib.TIMBER)
    implementation(Lib.BROWSER)

    // UI
    implementation(Lib.CONSTRAINT_LAYOUT)
    implementation(Lib.SWIPE_REFRESH_LAYOUT)
    implementation(Lib.MATERIAL)

    // Lifecycle
    implementation(Lib.LIFECYCLE_EXTENSIONS)
    implementation(Lib.LIFECYCLE_VIEWMODEL_KTX)
    implementation(Lib.LIFECYCLE_LIVEDATA_KTX)
    implementation(Lib.LIFECYCLE_VIEWMODEL_SAVEDSTATE)

    // Navigation
    implementation(Lib.NAVIGATION_FRAGMENT_KTX)
    implementation(Lib.NAVIGATION_UI_KTX)

    // Paging
    implementation(Lib.PAGING_KTX)

    // Glide
    implementation(Lib.GLIDE_RUNTIME)
    kapt(Lib.GLIDE_COMPILER)

    // Coroutine
    implementation(Lib.COROUTINES_CORE)
    implementation(Lib.COROUTINES_ANDROID)

    // WorkManager
    implementation(Lib.WORK_KTX)

    // Hilt
    implementation(Lib.HILT)
    kapt(Lib.HILT_COMPILER)
    implementation(Lib.HILT_ANDROID_VIEWMODEL)
    kapt(Lib.HILT_ANDROID_COMPILER)

    // Firebase
    implementation(platform(Lib.FIREBASE_BOM))
    implementation(Lib.FIREBASE_ANALYTICS_KTX)
    implementation(Lib.FIREBASE_CRASHLYTICS_KTX)
    implementation(Lib.FIREBASE_AUTH)

    // Testing
    testImplementation(Lib.JUNIT)
    androidTestImplementation(Lib.EXT_JUNIT)
    androidTestImplementation(Lib.ESPRESSO)

    // Preference
    implementation(Lib.PREFERENCE)
}
