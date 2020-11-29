// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven { url = uri("https://maven.fabric.io/public") }
    }
    dependencies {
        classpath(Lib.ANDROID_GRADLE_PLUGIN)
        classpath(Lib.KOTLIN_PLUGIN)
        classpath(Lib.KOTLIN_ALLOPEN)
        classpath(Lib.NAVIGATION_SAFE_ARGS)
        classpath(Lib.HILT_GRADLE_PLUGIN)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven { url = uri("https://maven.fabric.io/public") }
    }
}

tasks.register("clean", Delete::class){
    delete(project.buildDir)
}
