// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
    dependencies {
        classpath(Lib.ANDROID_GRADLE_PLUGIN)
        classpath(Lib.KOTLIN_PLUGIN)
        classpath(Lib.KOTLIN_ALLOPEN)
        classpath(Lib.NAVIGATION_SAFE_ARGS)
        classpath(Lib.HILT_GRADLE_PLUGIN)
        classpath(Lib.GOOGLE_SERVICE)
        classpath(Lib.FIREBASE_CRASHLYTICS_GRADLE)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
}

tasks.register("clean", Delete::class){
    delete(project.buildDir)
}
