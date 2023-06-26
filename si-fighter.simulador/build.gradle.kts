plugins {
    id("java")
}

group = "br.ufms"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(mapOf("path" to ":si-fighter.core")))
    implementation(project(mapOf("path" to ":si-fighter.core")))
    implementation(project(mapOf("path" to ":si-fighter.core")))
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}