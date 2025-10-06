plugins {
	id("org.springframework.boot")
	id("io.spring.dependency-management")
	java
}

dependencies {
	implementation(rootProject.libs.spring.boot.starter.web)
	implementation(rootProject.libs.spring.boot.starter.actuator)
	implementation(rootProject.libs.spring.boot.starter.validation)
	implementation(rootProject.libs.spring.boot.starter.data.redis)
	implementation(rootProject.libs.spring.kafka)
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")
	testImplementation(rootProject.libs.junit.jupiter)
}

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

springBoot {
	mainClass.set("com.shopsmart.checkout.CheckoutServiceApplication")
}
