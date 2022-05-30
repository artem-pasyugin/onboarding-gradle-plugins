package ru.onboarding.configure.microservice

import org.gradle.api.Action
import org.gradle.api.Project
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import org.gradle.api.provider.ProviderFactory
import org.gradle.api.tasks.Nested

abstract class MicroserviceConfigurerExtension {

    abstract Property<String> getSpringCloudVersion()

    abstract Property<String> getTestContainersVersion()

    @Nested
    abstract PublishData getPublish()

    void publish(Action<? super PublishData> action) {
        action.execute(publish)
    }

    MicroserviceDependencyPack dependencyPack

    MicroserviceConfigurerExtension(Project project) {
        dependencyPack = new MicroserviceDependencyPack(project)
        springCloudVersion.convention('2021.0.2')
        testContainersVersion.convention('1.17.2')
    }

    static abstract class PublishData {
        abstract Property<String> getDockerRegistry()

        PublishData(ProviderFactory providers, ObjectFactory objectFactory) {
            dockerRegistry.convention(
                    providers.environmentVariable('DOCKER_REGISTRY')
                            .orElse(
                                    objectFactory.property(String)
                                            .convention('eco-docker-snapshots.binary.alfabank.ru')
                            )
            )
        }
    }
}
