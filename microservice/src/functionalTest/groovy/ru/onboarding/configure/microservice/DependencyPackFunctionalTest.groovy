package ru.onboarding.configure.microservice

import nebula.test.IntegrationSpec
import org.gradle.testkit.runner.GradleRunner

class DependencyPackFunctionalTest extends IntegrationSpec {

    def setup() {
        buildFile << """
                plugins {
                        id '${MicroservicePlugin.class.packageName}'
                        }
                        microservice {
                            dependencyPack
                                    .microservice()
                                    .lombok()
                        }
                        """
    }

    def "can run tasks"() {
        when:
        def result = GradleRunner.create()
                .forwardOutput()
                .withPluginClasspath()
                .withArguments( "greetings", "greetingsMicroservice")
                .withProjectDir(projectDir)
                .build();
        then:
        result.output.contains("Hello from plugin '${MicroservicePlugin.class.name}'")
    }
}