plugins {
    id("java-library")
    kotlin("jvm")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

dependencies {

    // Kotlin
    implementation(Lib.KOTLIN_STDLIB)

    // Timber
    implementation(Lib.TIMBER)

    // Coroutine
    implementation(Lib.COROUTINES_CORE)
}
