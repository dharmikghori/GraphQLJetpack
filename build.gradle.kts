buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:4.1.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.31")
        classpath("com.google.dagger:hilt-android-gradle-plugin:2.48")
//        classpath("com.apollographql.apollo:apollo-gradle-plugin:2.5.4")
    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version "7.1.1" apply false
    id("org.jetbrains.kotlin.android") version "1.9.0" apply false
    id("com.apollographql.apollo3") version "3.8.2" apply false

}
