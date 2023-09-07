import com.sachin.android.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class FeatureUiConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("er.android.library")
                apply("er.android.library.compose")
            }

            dependencies {
                add("implementation", project(":core:ui"))
                add("implementation", libs.findLibrary("androidx.navigation.compose").get())
                add("implementation", libs.findLibrary("hilt.navigation").get())
                add("implementation", libs.findLibrary("lifecycle.runtime").get())
            }
        }
    }
}