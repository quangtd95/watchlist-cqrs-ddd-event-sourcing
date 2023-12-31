import com.qstudio.investing.COMMONS_DBCP2_VERSION
import com.qstudio.investing.JDBC_POSTGRES_VERSION
import com.qstudio.investing.SPRINGDOC_OPENAPI_STARTER_WEBFLUX_UI
import com.qstudio.investing.SPRING_BOOT_VERSION

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

    //jdbc postgres & connection pool for event store
    implementation("org.springframework.data:spring-data-jdbc:$SPRING_BOOT_VERSION")
    implementation("org.postgresql:postgresql:$JDBC_POSTGRES_VERSION")
    implementation("org.apache.commons:commons-dbcp2:$COMMONS_DBCP2_VERSION")

    //swagger
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:$SPRINGDOC_OPENAPI_STARTER_WEBFLUX_UI")

    implementation(project(":watchlist-shared-kernel"))
}

application {
    // Define the main class for the application.
    mainClass.set("com.qstudio.investing.watchlist_command.WatchlistCommandApp.kt")
}
