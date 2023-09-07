package com.sachin.android.buildlogic

import com.android.build.api.dsl.CommonExtension
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

/**
 * Configure Compose-specific options
 */
internal fun Project.configureAndroidCompose(commonExtension: CommonExtension<*, *, *, *, *>) {
    commonExtension.apply {
        buildFeatures {
            compose = true
        }

        composeOptions {
            kotlinCompilerExtensionVersion = libs.findVersion("compose-compiler").get().toString()
        }

        dependencies {
            val bom = libs.findLibrary("compose-bom").get()
            add("implementation", platform(bom))
            add("implementation", libs.findLibrary("ui").get())
            add("implementation", libs.findLibrary("ui-graphics").get())
            add("implementation", libs.findLibrary("ui-tooling-preview").get())
            add("implementation", libs.findLibrary("material3").get())
            add("androidTestImplementation", platform(bom).get())
            add("debugImplementation", libs.findLibrary("ui-tooling").get())
            add("debugImplementation", libs.findLibrary("ui-test-manifest").get())
        }
    }
}
