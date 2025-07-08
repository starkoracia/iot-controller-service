plugins {
	java
	id("org.springframework.boot") version "3.4.4"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "com.starkoracia"
version = "0.0.1"

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(17))
	}
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

repositories {
	mavenCentral()
}

dependencies {
	/**
	 * Spring boot starters
	 */
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	developmentOnly("org.springframework.boot:spring-boot-docker-compose")
	developmentOnly("org.springframework.boot:spring-boot-devtools")
	annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")

	/**
	 * Utils & Logging
	 */
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")
	implementation("org.mapstruct:mapstruct:1.5.5.Final")
	annotationProcessor("org.mapstruct:mapstruct-processor:1.5.5.Final")
	// Spring uses .env for environment variables
	implementation("me.paulschwarz:spring-dotenv:4.0.0")

	/**
	 * Database
	 */
	runtimeOnly("org.postgresql:postgresql")
	implementation("org.liquibase:liquibase-core")

	/**
	 * MQTT(Paho)
	 */
	implementation("org.eclipse.paho:org.eclipse.paho.client.mqttv3:1.2.5")

	/**
	 * Tests
	 */
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.test {
	useJUnitPlatform()
}

tasks.bootJar {
	archiveFileName.set("${project.name}.jar")
}

tasks.jar {
	enabled = false
}