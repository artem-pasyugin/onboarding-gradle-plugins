package ru.onboarding.configure.microservice

import nebula.test.IntegrationSpec
import org.gradle.testkit.runner.GradleRunner

class MicroserviceTest extends IntegrationSpec {

    def setup() {
        buildFile << """
                        plugins {
                                id('${MicroservicePlugin.class.packageName}')
                                }
                
                        microservice {
                            dependencyPack
                                    .microservice()
                                    .lombok()
                        }
                        """

        writeHelloWorld()
        writeUnitTest("""
            class MyFirstJUnitJupiterTests {

                @org.junit.jupiter.api.Test
                void addition() {
                    org.junit.jupiter.api.Assertions.assertEquals(2, 2);
                }
            }
        """)
    }

    def "should build and test successfully"() throws IOException {
        expect:
        GradleRunner.create()
                .forwardOutput()
                .withPluginClasspath()
                .withArguments( "build", "test")
                .withProjectDir(projectDir)
                .build();


    }

}
