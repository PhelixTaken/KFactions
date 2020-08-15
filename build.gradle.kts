plugins {
    kotlin("jvm") version "1.3.72"
}

group = "me.phelix.kfactions"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://nexus.savagelabs.net/repository/maven-releases/")
    maven ("https://papermc.io/repo/repository/maven-public/")
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("net.prosavage:BasePlugin:1.7.4")
    compileOnly("com.destroystokyo.paper:paper-api:1.16.1-R0.1-SNAPSHOT")
}

tasks.jar {
    archiveFileName.set("${project.name}-${project.version}.jar")
    destinationDirectory.set(file(".${File.separator}Server${File.separator}plugins"))
    from(configurations["runtimeClasspath"].map(::zipTree))
}