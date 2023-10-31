plugins {
    id("common-conventions")
}

dependencies {
    implementation("org.axonframework:axon-spring-boot-starter:4.9.0") {
        exclude(group = "org.axonframework", module = "axon-server-connector")
    }
    implementation("io.axoniq.console:console-framework-client-spring-boot-starter:1.0.2")
    implementation("org.axonframework.extensions.kotlin:axon-kotlin:4.9.0")
    implementation("org.axonframework.extensions.reactor:axon-reactor:4.9.0")
    implementation("org.axonframework.extensions.amqp:axon-amqp-spring-boot-starter:4.9.0")
}
