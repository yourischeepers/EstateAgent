import com.android.build.api.dsl.ApplicationDefaultConfig
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.gradle.api.artifacts.VersionCatalogsExtension
import java.util.Properties

val libsCatalog = extensions.getByType<VersionCatalogsExtension>().named("libs")
val buildTools = libsCatalog.findVersion("android-buildTools").get().requiredVersion

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlin.serialization)
}

val localProperties = Properties().apply {
    val file = rootProject.file("local.properties")
    if (file.exists()) {
        load(file.inputStream())
    }
}

val mapsApiKey = localProperties.getProperty("MAPS_API_KEY") ?: ""
val mapsStyleId = localProperties.getProperty("MAPS_STYLE_ID") ?: ""

android {
    namespace = "me.partypronl.estateagent"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    buildToolsVersion = buildTools

    androidResources {
        generateLocaleConfig = true
    }

    defaultConfig {
        applicationId = "me.partypronl.estateagent"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 3
        versionName = "1.0.2"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        manifestPlaceholders["MAPS_API_KEY"] = mapsApiKey

        buildConfigField("String", "MAPS_API_KEY", "\"$mapsApiKey\"")
        buildConfigField("String", "MAPS_STYLE_ID", "\"$mapsStyleId\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.toVersion(libs.versions.jvm.get().toInt())
        targetCompatibility = JavaVersion.toVersion(libs.versions.jvm.get().toInt())
        isCoreLibraryDesugaringEnabled = true
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    java {
        toolchain {
            languageVersion.set(JavaLanguageVersion.of(libs.versions.jvm.get().toInt()))
        }
    }

    kotlin {
        compilerOptions {
            jvmTarget.set(JvmTarget.fromTarget(libs.versions.jvm.get()))
        }
    }
}

dependencies {
    // Modules
    implementation(project(":domain"))
    implementation(project(":presentation"))
    implementation(project(":data-core"))
    implementation(project(":data-local"))
    implementation(project(":data-remote"))

    // Android
    implementation(libs.bundles.app)

    // Generic
    implementation(libs.bundles.kotlin.library)

    // Koin
    implementation(platform(libs.koin.bom))
    implementation(libs.bundles.koin.core)
    implementation(libs.bundles.koin.android)
    ksp(libs.koin.ksp)

    // Presentation
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.bundles.presentation)

    // Data local
    implementation(libs.bundles.data.local)
    ksp(libs.androidx.room.compiler)

    // Desugaring
    coreLibraryDesugaring(libs.desugar)
}

ksp {
    arg("KOIN_CONFIG_CHECK","true")
}
