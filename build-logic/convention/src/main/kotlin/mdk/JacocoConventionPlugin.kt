package mdk

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.testing.jacoco.plugins.JacocoPluginExtension
import org.gradle.testing.jacoco.tasks.JacocoReport

/**
 * Registers a `jacocoTestReport` task that depends on `testDebugUnitTest`.
 * Reports land at build/reports/jacoco/jacocoTestReport/{html,xml}.
 *
 * Requires the Android plugin (app or library) to be applied before or after this plugin —
 * wiring is done lazily via pluginManager.withPlugin.
 */
class JacocoConventionPlugin : Plugin<Project> {

    private val coverageExcludes = listOf(
        "**/R.class",
        "**/R$*.class",
        "**/BuildConfig.*",
        "**/Manifest*.*",
        "**/*Test*.*",
        "android/**/*.*",
        "**/databinding/**",
        "**/di/**",
        "**/*_Factory.*",
        "**/*_MembersInjector.*",
    )

    override fun apply(target: Project) {
        with(target) {
            pluginManager.apply("jacoco")

            extensions.configure<JacocoPluginExtension> {
                toolVersion = "0.8.12"
            }

            pluginManager.withPlugin("com.android.application") { registerJacocoTask() }
            pluginManager.withPlugin("com.android.library") { registerJacocoTask() }
        }
    }

    private fun Project.registerJacocoTask() {
        tasks.register("jacocoTestReport", JacocoReport::class.java) {
            group = "Verification"
            description = "Generates JaCoCo coverage report for debug unit tests."

            dependsOn("testDebugUnitTest")

            // AGP 8.x writes Kotlin class files under tmp/kotlin-classes/debug
            val kotlinClasses = fileTree(layout.buildDirectory.dir("tmp/kotlin-classes/debug")) {
                exclude(coverageExcludes)
            }

            classDirectories.setFrom(files(kotlinClasses))
            sourceDirectories.setFrom(
                files(
                    "${projectDir}/src/main/java",
                    "${projectDir}/src/main/kotlin",
                )
            )
            // AGP 8.x unit-test exec path when enableUnitTestCoverage = true
            executionData.setFrom(
                fileTree(layout.buildDirectory) {
                    include("outputs/unit_test_code_coverage/debugUnitTest/testDebugUnitTest.exec")
                }
            )

            reports {
                xml.required.set(true)
                html.required.set(true)
                csv.required.set(false)
                xml.outputLocation.set(
                    layout.buildDirectory.file("reports/jacoco/jacocoTestReport/jacocoTestReport.xml")
                )
                html.outputLocation.set(
                    layout.buildDirectory.dir("reports/jacoco/jacocoTestReport/html")
                )
            }
        }
    }
}
