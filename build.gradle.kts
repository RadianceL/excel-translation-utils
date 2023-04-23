plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.alibaba:easyexcel:3.2.1")
    implementation("com.google.code.gson:gson:2.10.1")
    implementation("com.alibaba:fastjson:1.2.83")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}