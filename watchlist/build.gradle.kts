import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("application-conventions")
    id("org.springframework.boot") version "3.1.5"
    id("io.spring.dependency-management") version "1.1.3"
    kotlin("plugin.spring") version "1.9.10"
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    annotationProcessor("org.projectlombok:lombok")

    implementation("jakarta.servlet:jakarta.servlet-api:6.0.0")

    implementation("org.axonframework:axon-spring-boot-starter:4.9.0")
    implementation("io.axoniq.console:console-framework-client-spring-boot-starter:1.0.2")

    //postgres
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("io.r2dbc:r2dbc-postgresql:0.8.13.RELEASE")


    //db migration
    implementation("org.liquibase:liquibase-core")
    implementation("org.postgresql:postgresql:42.5.4")

    implementation("org.apache.commons:commons-dbcp2:2.10.0")

    //swagger
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:2.2.0")



}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs += "-Xjsr305=strict"
        jvmTarget = "17"
    }
}

application {
    // Define the main class for the application.
    mainClass.set("com.qstudio.investing.app.AppKt")
}
