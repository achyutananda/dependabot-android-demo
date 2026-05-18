plugins {
    `kotlin-dsl`
}

group = "mdk.buildlogic"

dependencies {
    compileOnly(libs.build.android.gradlePlugin)
    compileOnly(libs.build.kotlin.gradlePlugin)
    compileOnly(libs.build.sonar.gradlePlugin)
    compileOnly(libs.build.dokka.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "mdk.android.application"
            implementationClass = "mdk.AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "mdk.android.library"
            implementationClass = "mdk.AndroidLibraryConventionPlugin"
        }
        register("sonar") {
            id = "mdk.sonar"
            implementationClass = "mdk.SonarConventionPlugin"
        }
        register("lint") {
            id = "mdk.lint"
            implementationClass = "mdk.LintConventionPlugin"
        }
        register("dokka") {
            id = "mdk.dokka"
            implementationClass = "mdk.DokkaConventionPlugin"
        }
        register("jacoco") {
            id = "mdk.jacoco"
            implementationClass = "mdk.JacocoConventionPlugin"
        }
    }
}
