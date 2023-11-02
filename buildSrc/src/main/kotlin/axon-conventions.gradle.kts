import com.qstudio.investing.SPRING_BOOT_VERSION
import com.qstudio.investing.AXON_VERSION
import com.qstudio.investing.AXONIQ_CONSOLE_VERSION
import com.qstudio.investing.MessageBusType

plugins {
    id("common-conventions")
}

dependencies {
    val messageBus = project.properties["appMessageBus"] as String

    implementation("org.axonframework:axon-spring-boot-starter:$AXON_VERSION") {
        if (messageBus.lowercase() != MessageBusType.AXON.name.lowercase()) {
            println("exclude axon-server-connector")
            exclude(group = "org.axonframework", module = "axon-server-connector")
        }
    }
    implementation("io.axoniq.console:console-framework-client-spring-boot-starter:$AXONIQ_CONSOLE_VERSION")
    implementation("org.axonframework.extensions.kotlin:axon-kotlin:$AXON_VERSION")
    implementation("org.axonframework.extensions.reactor:axon-reactor:$AXON_VERSION")

    //amqp
    implementation("org.springframework.boot:spring-boot-starter-amqp:$SPRING_BOOT_VERSION")
    implementation("org.axonframework.extensions.amqp:axon-amqp-spring-boot-starter:$AXON_VERSION")
}
