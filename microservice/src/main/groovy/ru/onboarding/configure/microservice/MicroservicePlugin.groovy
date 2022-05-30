package ru.onboarding.configure.microservice

import io.spring.gradle.dependencymanagement.DependencyManagementPlugin
import org.gradle.api.JavaVersion
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.publish.maven.plugins.MavenPublishPlugin
import org.springframework.boot.gradle.plugin.SpringBootPlugin
import ru.onboarding.configure.base.BasePlugin
import ru.onboarding.configure.microservice.configurers.SpringBomConfigurer

class MicroservicePlugin implements Plugin<Project> {

    private static final String MICROSERVICE_EXTENSION_NAME = "microservice";
    private static final JavaVersion JAVA_VERSION = JavaVersion.VERSION_17;

    @Override
    void apply(Project project) {
        BasePlugin.checkGradleVersion()
        project.extensions.create(MICROSERVICE_EXTENSION_NAME, MicroserviceConfigurerExtension, project)
        subPlugins().each { project.plugins.apply(it) }
        configureSource(project)

        project.tasks.register("greetingsMicroservice") {
            doLast {
                println("Hello from plugin '${MicroservicePlugin.class.name}'")
            }
        }
    }

    private static void configureSource(Project project) {
        def javaExtension = project.extensions.getByType(JavaPluginExtension)
        javaExtension.sourceCompatibility = JAVA_VERSION
        javaExtension.targetCompatibility = JAVA_VERSION
    }

    static List<Class<? extends Plugin>> subPlugins() {
        return [
                BasePlugin,
                SpringBootPlugin,
                DependencyManagementPlugin,
                MavenPublishPlugin,
                SpringBomConfigurer
        ]
    }
}
