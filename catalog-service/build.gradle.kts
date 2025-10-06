plugins {
	id("org.springframework.boot")
	id("io.spring.dependency-management")
	java
}

dependencies {
	implementation(rootProject.libs.spring.boot.starter.web)
	implementation(rootProject.libs.spring.boot.starter.actuator)
	implementation(rootProject.libs.spring.boot.starter.data.jpa)
	implementation(rootProject.libs.spring.boot.starter.validation)
	implementation(rootProject.libs.spring.boot.starter.cache)
	implementation(rootProject.libs.spring.boot.starter.data.redis)
	implementation(rootProject.libs.postgresql)
	implementation(rootProject.libs.flyway.core)
	implementation(rootProject.libs.mapstruct)
	annotationProcessor(rootProject.libs.mapstruct.processor)
	implementation(project(":common"))
	testImplementation(rootProject.libs.junit.jupiter)
}

java {
	sourceCompatibility = JavaVersion.VERSION_21
}

springBoot {
	mainClass.set("com.shopsmart.catalog.CatalogServiceApplication")
}
