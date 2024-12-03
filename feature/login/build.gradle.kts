plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.hilt.android)
    alias(libs.plugins.kapt)
    alias(libs.plugins.parcelable)
    alias(libs.plugins.paparazzi)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.kotlin.compose.compiler)
}

android {
    namespace = "com.example.feature.login"
    compileSdk = libs.versions.compileSdkVersion.get().toInt()

    defaultConfig {
        minSdk = libs.versions.minSdkVersion.get().toInt()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        val javaVersion = libs.versions.javaVersion.get()
        sourceCompatibility = JavaVersion.toVersion(javaVersion)
        targetCompatibility = JavaVersion.toVersion(javaVersion)
    }
    kotlinOptions {
        jvmTarget = libs.versions.javaVersion.get()
    }

    buildFeatures {
        compose = true
    }

    testOptions.unitTests {
        isReturnDefaultValues = true
        all { tests ->
            tests.useJUnitPlatform()
            tests.testLogging {
                events("passed", "failed", "skipped")
            }
        }
    }
}

dependencies {
    implementation(projects.core.view)
    implementation(libs.bundles.androidx)
    implementation(libs.bundles.hilt)

    kapt(libs.hilt.compiler)

    testRuntimeOnly(libs.junit.jupiter.engine)
}