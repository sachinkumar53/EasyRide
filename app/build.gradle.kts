plugins {
    id("er.android.application")
    id("er.android.application.compose")
    id("er.android.hilt")
    id("com.google.gms.google-services")

}

android {
    namespace = "com.sachin.app.easyride"

    defaultConfig {
        applicationId = "com.sachin.app.easyride"
        versionCode = 1
        versionName = "1.0"
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
}

dependencies {
    implementation(libs.core.ktx)
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.activity.compose)

    implementation(project(":core:ui"))
    implementation(project(":core:data"))
    implementation(project(":feature:onboarding:ui"))
    implementation(project(":feature:authentication:ui"))

    implementation(libs.androidx.navigation.compose)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
    androidTestImplementation(platform(libs.compose.bom))
    androidTestImplementation(libs.ui.test.junit4)
    debugImplementation(libs.ui.tooling)
    debugImplementation(libs.ui.test.manifest)
}