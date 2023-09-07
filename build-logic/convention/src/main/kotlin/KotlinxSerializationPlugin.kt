import com.sachin.android.buildlogic.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class KotlinSerializationPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("kotlinx-serialization")
            dependencies {
                add("implementation", libs.findLibrary("kotlinx.serialization.json").get())
            }
        }
    }
}