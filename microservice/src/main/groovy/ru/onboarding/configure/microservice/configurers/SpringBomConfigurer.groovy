package ru.onboarding.configure.microservice.configurers

import groovy.transform.CompileStatic
import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension
import io.spring.gradle.dependencymanagement.dsl.ImportsHandler
import org.gradle.api.Action
import org.gradle.api.Plugin
import org.gradle.api.Project
import ru.onboarding.configure.microservice.MicroserviceConfigurerExtension

@CompileStatic
class SpringBomConfigurer implements Plugin<Project> {

    @Override
    void apply(Project project) {
        def extension = project.extensions.getByType(MicroserviceConfigurerExtension)

        project.afterEvaluate {
            project.extensions.getByType(DependencyManagementExtension).with {
                imports new Action<ImportsHandler>() {

                    @Override
                    void execute(ImportsHandler handler) {
                        handler.mavenBom "org.springframework.cloud:spring-cloud-dependencies:${extension.springCloudVersion.get()}"
                        handler.mavenBom "org.testcontainers:testcontainers-bom:${extension.testContainersVersion.get()}"
                    }
                }
            }
        }
    }
}
