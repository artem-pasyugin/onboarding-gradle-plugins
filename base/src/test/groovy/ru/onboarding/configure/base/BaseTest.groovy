package ru.onboarding.configure.base

import nebula.test.IntegrationSpec
import spock.lang.TempDir


abstract class BaseTest extends IntegrationSpec {
    @TempDir
    File projectDir
    File buildFile
    File sourceFile;

    def setup() {
        buildFile = new File(projectDir, 'build.gradle')
        def container = projectDir.toPath().resolve("src/test/java/").toFile()
        container.mkdirs()
        sourceFile = new File(container, "HelloWorldTest.java")
        writeHelloWorld()
    }

}
