package ru.onboarding.configure.base

class GradleVersionCheckTest extends BaseTest {

    def setup() {
        buildFile << applyPlugin(BasePlugin)
    }

    def "should fail if gradle version is not supported"() {
        given:
        gradleVersion = '7.0'

        expect:
        runTasksWithFailure('tasks')
    }

    def "should work with supported version"() {
        expect:
        runTasksSuccessfully('tasks')
    }
}
