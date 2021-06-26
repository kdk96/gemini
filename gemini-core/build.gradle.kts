plugins {
    id("ktlint-plugin")
    kotlin("multiplatform")
}

val geminiGroup = findProperty("group") as String
val geminiVersion = findProperty("version") as String

version = geminiVersion
group = geminiGroup

kotlin {
    jvm()
    ios()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Deps.kotlinx.coroutines)
            }
        }
    }
}
