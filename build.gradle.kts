plugins {
	id("org.springframework.boot") version "3.3.3" apply false
	id("io.spring.dependency-management") version "1.1.6" apply false
	java
}

java {
	sourceCompatibility = JavaVersion.VERSION_21
	targetCompatibility = JavaVersion.VERSION_21
}

allprojects {
	group = "com.shopsmart"
	version = "0.1.0-SNAPSHOT"

	repositories {
		mavenCentral()
	}
}

subprojects {
	apply(plugin = "io.spring.dependency-management")

	dependencies {
		"testImplementation"(platform("org.junit:junit-bom:${rootProject.libs.versions.junit.get()}"))
		"testImplementation"("org.junit.jupiter:junit-jupiter")
	}

	tasks.withType<Test>().configureEach {
		useJUnitPlatform()
	}
}
