package mdk

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.jetbrains.dokka.gradle.DokkaTask

/**
 * Configures dokkaHtml to output to build/docs/html.
 * Run: ./gradlew :library:dokkaHtml
 */
class DokkaConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.jetbrains.dokka")

            tasks.withType(DokkaTask::class.java).configureEach {
                outputDirectory.set(layout.buildDirectory.dir("docs/html"))

                dokkaSourceSets.configureEach {
                    skipEmptyPackages.set(true)
                    skipDeprecated.set(false)
                    reportUndocumented.set(true)
                    includeNonPublic.set(false)
                }
            }
        }
    }
}
