plugins {
    kotlin("multiplatform")
    kotlin("plugin.serialization").version(Versions.KOTLIN)
    id("com.chromaticnoise.multiplatform-swiftpackage").version(Versions.SPM_PLUGIN)
    id("com.vanniktech.maven.publish")
    id("com.android.library")
    id("io.gitlab.arturbosch.detekt")
    id("org.jlleitschuh.gradle.ktlint")
    id("org.jetbrains.kotlinx.kover")
    id("githook-install")
    id("spm-tasks")
}

kover {
    verify {
        rule {
            name = "Minimal line coverage rate in percents"
            bound {
                minValue = 90
            }
        }
    }
}

val iosLibraryName = properties["IOS_NAME"].toString()
version = properties["VERSION_NAME"].toString()

multiplatformSwiftPackage {
    packageName(iosLibraryName)
    swiftToolsVersion("5.3")
    outputDirectory(File(rootDir, "/"))
    targetPlatforms {
        iOS { v("13") }
    }
}

kotlin {
    android()
    jvm()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = iosLibraryName
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Dependencies.Network.KTOR_NEGOTIATION)
                implementation(Dependencies.Network.KTOR_SERIALIZATION)
                implementation(Dependencies.Network.KTOR_CORE)
                implementation(Dependencies.Network.KTOR_LOGGING)
                implementation(Dependencies.DI.KOIN_CORE)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
                implementation(Dependencies.Test.MOCKK_COMMON)
                implementation(Dependencies.Test.KTOR)
                implementation(Dependencies.Test.KOIN)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(Dependencies.Network.KTOR_OKHTTP)
            }
        }
        val androidTest by getting {
            dependencies {
                implementation(Dependencies.Test.MOCKK)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(Dependencies.Network.KTOR_IOS)
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
        val jvmMain by getting {
            dependencies {
                implementation(Dependencies.Network.KTOR_OKHTTP)
            }
        }
        val jvmTest by getting {
            dependencies {
                implementation(Dependencies.Test.MOCKK_JVM)
            }
        }
    }
}

android {
    namespace = "co.yml.ychat"
    compileSdk = Config.COMPILE_SDK_VERSION
    defaultConfig {
        minSdk = Config.MIN_SDK_VERSION
        targetSdk = Config.TARGET_SDK_VERSION
    }
}

mavenPublishing {
    pom {
        developers {
            developer {
                id.set("osugikoji")
                name.set("Koji Osugi")
                url.set("https://github.com/osugikoji")
            }
            developer {
                id.set("renatoarg")
                name.set("Renato Goncalves")
                url.set("https://github.com/renatoarg")
            }
            developer {
                id.set("kikoso")
                name.set("Enrique López Mañas")
                url.set("https://github.com/kikoso")
            }
        }
    }
}
