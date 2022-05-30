import nebula.test.IntegrationSpec
import org.gradle.testkit.runner.GradleRunner
import ru.onboarding.configure.base.BasePlugin
import ru.onboarding.configure.base.plugin.BaseCheckstylePlugin

class CodeStyleFunctionalTest extends IntegrationSpec {

    def setup() {
        buildFile << """
                plugins {
                        id '${BasePlugin.class.packageName}'
                        }
                        """

        writeHelloWorld()
    }

    def "should add code styles configs"() {
        when:
        GradleRunner.create()
                .forwardOutput()
                .withPluginClasspath()
                .withArguments( "check")
                .withProjectDir(projectDir)
                .build();

        then:
        file(BaseCheckstylePlugin.RESOURCE_CONFIG_FILE).text == CodeStyleFunctionalTest.getResource(BaseCheckstylePlugin.RESULT_CONFIG_FILE).text
    }
}
