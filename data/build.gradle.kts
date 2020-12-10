plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdkVersion(Version.COMPILE_SDK)
    buildToolsVersion(Version.BUILD_TOOL)
    defaultConfig {
        minSdkVersion(Version.MIN_SDK)
        targetSdkVersion(Version.TARGET_SDK)
        versionCode = Version.VERSION_CODE
        versionName = Version.VERSION_NAME
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    buildTypes {
        getByName("release") {
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
}

dependencies {

    implementation(project(":domain"))

    // Core
    implementation(Lib.TIMBER)

    // Kotlin
    implementation(Lib.KOTLIN_STDLIB)
    implementation(Lib.CORE_KTX)

    // Room
    implementation(Lib.ROOM_KTX)
    kapt(Lib.ROOM_COMPILER)

    // Paging
    implementation(Lib.PAGING_KTX)

    // Coroutine
    implementation(Lib.COROUTINES_CORE)
    implementation(Lib.COROUTINES_ANDROID)

    // Retrofit and neet
    implementation(Lib.OKHTTP_LOGGING_INTERCEPTOR)
    implementation(Lib.RETROFIT_RUNTIME)
    implementation(Lib.RETROFIT_GSON)

    // Hilt
    implementation(Lib.HILT)
    kapt(Lib.HILT_COMPILER)

    // WorkManager
    implementation(Lib.WORK_KTX)

    // Test
    implementation(Lib.JUNIT)
    implementation(Lib.EXT_JUNIT)
    implementation(Lib.ESPRESSO)
}
