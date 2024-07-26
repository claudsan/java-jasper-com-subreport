plugins {
    id("java")
}

group = "br.com.claudsan"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation ("net.sf.jasperreports:jasperreports:6.21.2")
}

tasks.test {
    useJUnitPlatform()
}