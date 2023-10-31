import com.qstudio.investing.JDK_VERSION
import com.qstudio.investing.JVM_TARGET_VERSION
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm")
}

repositories {
    mavenCentral()
}

dependencies {
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

java {
    sourceCompatibility = JDK_VERSION
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = JVM_TARGET_VERSION
    }
}
