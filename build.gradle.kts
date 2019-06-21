import com.moowork.gradle.node.npm.NpmInstallTask
import com.moowork.gradle.node.task.NodeTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.asciidoctor.convert") version "1.5.3"
    kotlin("plugin.jpa") version "1.2.71"
    id("org.springframework.boot") version "2.1.5.RELEASE"
    id("io.spring.dependency-management") version "1.0.7.RELEASE"
    kotlin("jvm") version "1.2.71"
    kotlin("plugin.spring") version "1.2.71"
    id("com.github.johnrengelman.shadow") version "5.0.0"
    id("com.moowork.node") version "1.3.1"
    java
    id ("org.jetbrains.kotlin.plugin.allopen") version "1.2.71"
}

group = "com.tasks"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
    mavenCentral()
}

extra["snippetsDir"] = file("build/generated-snippets")
/*
tasks {
    register("bundle",NodeTask::class){
        inputs.files(fileTree("node_modules"))
        inputs.files(fileTree("frontsrc"))
        inputs.file("package.json")
        inputs.file("webpack.config.js")
        outputs.dir("build/resources/static")
        dependsOn(NpmInstallTask.NAME)
        setScript(File("npm"))
        addArgs( "run", "build")
    }
    withType<ProcessResources> {

        dependsOn(NpmInstallTask.NAME)
    }

}
*/

    dependencies {
        implementation("org.springframework.boot:spring-boot-starter-activemq")
        implementation("org.springframework.boot:spring-boot-starter-actuator")
        implementation("org.springframework.boot:spring-boot-starter-data-jpa")
        implementation("org.springframework.boot:spring-boot-starter-hateoas")
        implementation("org.springframework.boot:spring-boot-starter-mail")
        implementation("org.springframework.boot:spring-boot-starter-web")
        implementation("org.springframework.boot:spring-boot-starter-webflux")
//        implementation("org.springframework.boot:spring-boot-starter-retry")
        implementation("org.springframework.boot:spring-boot-starter-batch")
        implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
        implementation("org.flywaydb:flyway-core")
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        runtimeOnly("com.h2database:h2")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("io.projectreactor:reactor-test")
        testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions {
            freeCompilerArgs = listOf("-Xjsr305=strict")
            jvmTarget = "1.8"
        }
    }
allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
    annotation("org.springframework.stereotype.Repository")
}
//tasks.test {
////	outputs.dir(snippetsDir)
//}
//
//tasks.asciidoctor {
////	inputs.dir(snippetsDir)
//	dependsOn(test)
//}
