package ru.onboarding.configure.base.plugin

import org.gradle.api.file.Directory
import org.gradle.api.plugins.quality.CheckstylePlugin
import org.gradle.api.plugins.quality.CodeQualityExtension

class BaseCheckstylePlugin extends CheckstylePlugin {
    static final String RESOURCE_CONFIG_FILE = "/checkstyle.xml"
    static final String RESULT_CONFIG_FILE = "checkstyle.xml"


    @Override
    protected CodeQualityExtension createExtension() {
        CodeQualityExtension extension = super.createExtension()
        extension.setToolVersion("10.2")
        Directory directory = this.project.getRootProject().getLayout().getProjectDirectory()
        extension.getConfigDirectory().convention(directory)
        extension.setConfig(this.project.getResources().getText().fromFile(extension.getConfigDirectory().file(RESULT_CONFIG_FILE).orElse(directory.file(RESULT_CONFIG_FILE))))
        return extension;
    }

    @Override
    protected void beforeApply() {
        def checkstyleFile = this.project.getRootProject().getLayout().getProjectDirectory().file(RESULT_CONFIG_FILE).asFile
        def readCheckstyleLines = BaseCheckstylePlugin.getResourceAsStream(RESOURCE_CONFIG_FILE).text
        checkstyleFile.text = readCheckstyleLines
        super.beforeApply()
    }

}