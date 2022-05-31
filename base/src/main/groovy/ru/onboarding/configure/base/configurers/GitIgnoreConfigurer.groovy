package ru.onboarding.configure.base.configurers

import com.fizzpod.gradle.plugins.gitignore.GitignorePluginExtension
import groovy.transform.CompileStatic
import org.gradle.api.Plugin
import org.gradle.api.Project

import static org.gradle.language.base.plugins.LifecycleBasePlugin.BUILD_TASK_NAME
import static org.gradle.language.base.plugins.LifecycleBasePlugin.CLEAN_TASK_NAME

@CompileStatic
class GitIgnoreConfigurer implements Plugin<Project> {

    static final String WRITE_GITIGNORE_TASK_NAME = 'writeGitignore'

    @Override
    void apply(Project project) {
        if (project == project.rootProject) {
            project.tasks.named(CLEAN_TASK_NAME).configure {
                it.dependsOn WRITE_GITIGNORE_TASK_NAME
            }
            project.tasks.named(BUILD_TASK_NAME).configure {
                it.dependsOn WRITE_GITIGNORE_TASK_NAME
            }
        }

        project.extensions.configure(GitignorePluginExtension) {
            it.ignores.addAll(gitIgnorePatterns())
        }
    }

    static List<String> gitIgnorePatterns() {
        return GitIgnoreConfigurer.getResourceAsStream('/defaultGitIgnore')
                .text
                .readLines()
    }
}
