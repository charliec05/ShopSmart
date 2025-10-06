plugins {
	id("org.springframework.boot")
	id("io.spring.dependency-management")
	java
}

dependencies {
	implementation(rootProject.libs.spring.cloud.config.server)
	implementation(rootProject.libs.spring.boot.starter.actuator)
	testImplementation(rootProject.libs.junit.jupiter)
}

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

springBoot {
	mainClass.set("com.shopsmart.config.ConfigServerApplication")
}
