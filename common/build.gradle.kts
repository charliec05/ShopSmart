plugins {
	`java-library`
}

dependencies {
	api("org.slf4j:slf4j-api:2.0.13")
	testImplementation(rootProject.libs.junit.jupiter)
}

java {
	withSourcesJar()
}
