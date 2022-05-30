package ru.onboarding.configure.microservice

import org.gradle.api.Project
import ru.onboarding.configure.base.BaseDependencyPack

final class MicroserviceDependencyPack extends BaseDependencyPack<MicroserviceDependencyPack> {

    MicroserviceDependencyPack(Project project) {
        super(project)
    }


    MicroserviceDependencyPack springBase() {
        this.base()
        project.with {
            dependencies {
                annotationProcessor 'org.springframework.boot:spring-boot-configuration-processor'
                implementation 'org.springframework.cloud:spring-cloud-starter-bootstrap'
                implementation 'org.springframework.cloud:spring-cloud-starter-config'
                implementation 'org.springframework.cloud:spring-cloud-starter-sleuth'
                implementation 'org.springframework.boot:spring-boot-starter-validation'
                implementation 'org.springframework.boot:spring-boot-starter-actuator'
                implementation 'org.codehaus.groovy:groovy-json'
                implementation 'net.logstash.logback:logstash-logback-encoder:7.2'
                testImplementation 'org.springframework.boot:spring-boot-starter-test'
            }
        }
        return this
    }

    MicroserviceDependencyPack microservice() {
        this.springBase()
        project.with {
            dependencies {
                implementation 'org.springframework.boot:spring-boot-starter-web'
            }
        }
        return this
    }


}
