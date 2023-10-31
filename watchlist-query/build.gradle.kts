import com.qstudio.investing.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("application-conventions")
    id("axon-conventions")
    id("kotlin-etx-conventions")
    id("spring-conventions")
}

dependencies {
    //rest api & validation
    implementation("org.springframework.boot:spring-boot-starter-webflux:$SPRING_BOOT_VERSION")
    implementation("org.springframework.boot:spring-boot-starter-validation:$SPRING_BOOT_VERSION")


    //postgres
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc:$SPRING_BOOT_VERSION")
    implementation("io.r2dbc:r2dbc-postgresql:$R2DBC_POSTGRES_VERSION")

    //db migration
    implementation("org.liquibase:liquibase-core:$LIQUIBASE_CORE_VERSION")

    //jdbc & connection pool for token store
    implementation("org.postgresql:postgresql:$JDBC_POSTGRES_VERSION")
    implementation("org.apache.commons:commons-dbcp2:$COMMONS_DBCP2_VERSION")

    //swagger
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:2.2.0")

    //amqp
    implementation("org.springframework.boot:spring-boot-starter-amqp:$SPRING_BOOT_VERSION")

    implementation(project(":watchlist-shared-kernel"))

}

application {
    // Define the main class for the application.
    mainClass.set("com.qstudio.investing.watchlist_query.WatchlistQueryApp.kt")
}
