package mdk

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.Lint
import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project

class LintConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.withPlugin("com.android.application") {
                extensions.configure(ApplicationExtension::class.java) {
                    lint.configure(layout.buildDirectory.asFile.get().path)
                }
            }
            pluginManager.withPlugin("com.android.library") {
                extensions.configure(LibraryExtension::class.java) {
                    lint.configure(layout.buildDirectory.asFile.get().path)
                }
            }
        }
    }

    private fun Lint.configure(buildDir: String) {
        abortOnError = false
        warningsAsErrors = true
        checkReleaseBuilds = true
        checkDependencies = true
        xmlReport = true
        htmlReport = true
        xmlOutput = java.io.File("$buildDir/reports/lint-results-debug.xml")
        htmlOutput = java.io.File("$buildDir/reports/lint-results-debug.html")
        baseline = java.io.File("lint-baseline.xml")
    }
}
