// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose.compiler) apply false
    alias(libs.plugins.kotlin.serialization) apply false
    alias(libs.plugins.hilt.android) apply false
    alias(libs.plugins.kapt) apply false
    alias(libs.plugins.parcelable) apply false
    alias(libs.plugins.paparazzi) apply false
    alias(libs.plugins.jetbrains.kotlin.jvm) apply false
}