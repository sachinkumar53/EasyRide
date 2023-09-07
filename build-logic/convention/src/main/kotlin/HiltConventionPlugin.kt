import com.sachin.android.buildlogic.libs
import dagger.hilt.android.plugin.HiltExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class HiltConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.google.dagger.hilt.android")
                apply("kotlin-kapt")
            }

            hilt {
                enableAggregatingTask = true
            }

            dependencies {
                add("implementation", libs.findLibrary("hilt.android").get())
                add("kapt", libs.findLibrary("hilt.compiler").get())
            }
        }
    }

}

private inline fun Project.hilt(crossinline block: HiltExtension.() -> Unit) {
    extensions.configure<HiltExtension> {
        block()
    }
}

