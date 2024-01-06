plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version("7.1.2")
}

group = "net.weekcharge"
version = "1.0-SNAPSHOT"

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

repositories {
    mavenCentral()
}

tasks.getByName("build") {
    dependsOn("shadowJar")
}

dependencies {
    implementation("org.ow2.asm:asm-tree:9.3")
}

tasks.getByName<Jar>("jar") {
    manifest.attributes(
        mapOf(
            "Can-Retransform-Classes" to true,
            "Premain-Class" to "net.weekcharge.Agent",
            "Agent-Class" to "net.weekcharge.Agent"
        )
    )
}