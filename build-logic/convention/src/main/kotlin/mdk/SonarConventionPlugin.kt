package mdk

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.sonarqube.gradle.SonarExtension

/**
 * Apply at root project to scan all submodules, or per module for targeted analysis.
 *
 * Required project properties (set via gradle.properties or CI env):
 *   sonar.host.url, sonar.token
 */
class SonarConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("org.sonarqube")

            extensions.configure<SonarExtension> {
                properties {
                    property("sonar.projectKey", "${project.group}:${project.name}")
                    property("sonar.projectName", project.name)
                    property("sonar.sources", "src/main")
                    property("sonar.tests", "src/test,src/androidTest")
                    property(
                        "sonar.coverage.jacoco.xmlReportPaths",
                        "${layout.buildDirectory.asFile.get()}/reports/jacoco/jacocoTestReport/jacocoTestReport.xml"
                    )
                    property(
                        "sonar.android.lint.report",
                        "${layout.buildDirectory.asFile.get()}/reports/lint-results-debug.xml"
                    )
                    property("sonar.kotlin.file.suffixes", ".kt,.kts")
                }
            }
        }
    }
}
