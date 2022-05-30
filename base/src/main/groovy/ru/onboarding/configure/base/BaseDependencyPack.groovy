package ru.onboarding.configure.base;

import org.gradle.api.Project;

class BaseDependencyPack<T extends BaseDependencyPack> {

    protected final Project project;

    BaseDependencyPack(Project project) {
        this.project = project;
    }

    T base() {
        project.with {
            dependencies {
                implementation 'org.apache.commons:commons-lang3:3.12.0'
                testImplementation 'org.mockito:mockito-core:4.5.1'
                testImplementation 'org.junit.jupiter:junit-jupiter:5.8.2'
            }
        }
        return this
    }

    T lombok() {
        project.with {
            dependencies {
                implementation 'javax.annotation:javax.annotation-api:1.3.2'
                compileOnly 'org.projectlombok:lombok:1.18.24'
                annotationProcessor 'org.projectlombok:lombok:1.18.24'
                testCompileOnly 'org.projectlombok:lombok:1.18.24'
                testAnnotationProcessor 'org.projectlombok:lombok:1.18.24'
            }
        }
        return this
    }
}
