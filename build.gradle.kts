plugins {
    kotlin("jvm") version "1.9.0"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

application {
    mainClass.set("com.example.MainKt")
}

repositories {
    mavenCentral()
    maven("https://jitpack.io")
}

dependencies {

    implementation("eu.vendeli:telegram-bot:2.1.0")
    implementation("org.telegram", "telegrambots", "5.1.1")
    implementation("io.github.kotlin-telegram-bot.kotlin-telegram-bot:telegram:6.1.0")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}