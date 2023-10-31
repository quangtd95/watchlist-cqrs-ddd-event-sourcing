import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("application-conventions")
    id("axon-conventions")
    id("kotlin-etx-conventions")
    id("spring-conventions")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-validation")


    //postgres
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("io.r2dbc:r2dbc-postgresql:0.8.13.RELEASE")


    //db migration
    implementation("org.liquibase:liquibase-core")
    implementation("org.postgresql:postgresql:42.5.4")

    implementation("org.apache.commons:commons-dbcp2:2.10.0")

    //swagger
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:2.2.0")

    implementation(project(":watchlist-shared-kernel"))



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
