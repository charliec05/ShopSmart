plugins {
	id("org.springframework.boot")
	id("io.spring.dependency-management")
	java
}

dependencies {
	implementation(rootProject.libs.spring.cloud.starter.gateway)
	implementation(rootProject.libs.spring.boot.starter.actuator)
	implementation(rootProject.libs.spring.boot.starter.security)
	implementation(rootProject.libs.spring.cloud.starter.bootstrap)
	testImplementation(rootProject.libs.junit.jupiter)
}

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

springBoot {
	mainClass.set("com.shopsmart.gateway.ApiGatewayApplication")
}
