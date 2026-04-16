import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension

plugins {
	java
	id("org.springframework.boot") version "4.0.3" apply false;
	id("io.spring.dependency-management") version "1.1.7" apply false;
}

allprojects {
	group = "com.frauddetection"
	version = "0.0.1-SNAPSHOT"
	repositories {
		mavenCentral()
	}
}

subprojects {
	apply(plugin = "java")
	apply(plugin = "io.spring.dependency-management")

	the<DependencyManagementExtension>().imports {
		mavenBom("org.springframework.boot:spring-boot-dependencies:4.0.3")
	}

	java {
		toolchain {
			languageVersion = JavaLanguageVersion.of(22)
		}
	}

	dependencies {
		implementation("org.springframework.boot:spring-boot-starter")
		implementation("org.springframework.boot:spring-boot-starter-validation")
		testImplementation("org.springframework.boot:spring-boot-starter-test")
		testRuntimeOnly("org.junit.platform:junit-platform-launcher")

		compileOnly("org.projectlombok:lombok")
		annotationProcessor("org.projectlombok:lombok")
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}
}
