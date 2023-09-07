package com.sachin.android.buildlogic

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.dependencies
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

/**
 * Configure base Kotlin with Android options
 */
internal fun Project.configureKotlinAndroid(commonExtension: CommonExtension<*, *, *, *, *>) {
    commonExtension.apply {
        compileSdk = 34

        defaultConfig {
            minSdk = 24
            vectorDrawables.useSupportLibrary = true
            testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        }

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_17
            targetCompatibility = JavaVersion.VERSION_17
//            isCoreLibraryDesugaringEnabled = true
        }

        kotlinOptions {
            // Set JVM target to 17
            jvmTarget = JavaVersion.VERSION_17.toString()
        }
    }

    dependencies {
        //add("implementation", libs.findLibrary("kotlinx-coroutines").get())
        //add("coreLibraryDesugaring", libs.findLibrary("coreDesugarJdk").get())
    }
}

private fun CommonExtension<*, *, *, *, *>.kotlinOptions(block: KotlinJvmOptions.() -> Unit) {
    (this as ExtensionAware).extensions.configure("kotlinOptions", block)
}