import nebula.test.IntegrationSpec
import org.gradle.testkit.runner.GradleRunner
import ru.onboarding.configure.base.BasePlugin

class GitIgnoreFunctionalTest extends IntegrationSpec {

    def setup() {
        buildFile << """
                plugins {
                        id '${BasePlugin.class.packageName}'
                        }
            
                gitignore {
                    ignore "additional"
                          }
        """
    }

    def "should add gitignore on build"() {
        when:
        GradleRunner.create()
                .forwardOutput()
                .withPluginClasspath()
                .withArguments( "assemble", "build")
                .withProjectDir(projectDir)
                .build();


        then:
        fileExists(".gitignore")
        file(".gitignore").text.contains('\nbuild/\n')
        file(".gitignore").text.endsWith('\nadditional\n')
    }

}
