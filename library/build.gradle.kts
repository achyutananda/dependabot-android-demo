plugins {
    alias(libs.plugins.mdk.android.library)
    alias(libs.plugins.mdk.lint)
    alias(libs.plugins.mdk.dokka)
    alias(libs.plugins.mdk.jacoco)
    alias(libs.plugins.mdk.sonar)
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
