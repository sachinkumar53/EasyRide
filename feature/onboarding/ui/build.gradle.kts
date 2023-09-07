plugins {
    id("er.android.library")
    id("er.android.library.compose")
}

android {
    namespace = "com.sachin.app.easyride.onboarding.ui"
}

dependencies {
    implementation(project(":core:ui"))
    implementation(libs.androidx.navigation.compose)
}