import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "com.sachin.android.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}


dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.hilt.gradlePlugin)
    compileOnly(libs.crashlytics.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "er.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidLibrary") {
            id = "er.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidApplicationCompose") {
            id = "er.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibraryCompose") {
            id = "er.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }

        register("hilt") {
            id = "er.android.hilt"
            implementationClass = "HiltConventionPlugin"
        }

        register("kotlinxSerialization") {
            id = "er.android.serialization"
            implementationClass = "KotlinSerializationPlugin"
        }

        register("featureUi") {
            id = "er.android.feature.ui"
            implementationClass = "FeatureUiConventionPlugin"
        }
    }
}