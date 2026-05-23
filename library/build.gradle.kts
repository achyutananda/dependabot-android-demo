plugins {
    alias(libs.plugins.slab.android.library)
    alias(libs.plugins.slab.lint)
    alias(libs.plugins.slab.dokka)
    alias(libs.plugins.slab.jacoco)
    alias(libs.plugins.slab.sonar)
}

android {
    namespace = "com.slab.library"
}

dependencies {
    implementation(libs.androidx.core.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}
