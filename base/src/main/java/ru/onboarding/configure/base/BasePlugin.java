package ru.onboarding.configure.base;

import com.fizzpod.gradle.plugins.gitignore.GitignorePlugin;
import org.gradle.api.GradleException;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.GroovyPlugin;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.plugins.quality.CheckstylePlugin;
import org.gradle.util.GradleVersion;

import java.util.List;

public class BasePlugin implements Plugin<Project> {
    private static final GradleVersion GRADLE_MIN_VERSION = GradleVersion.version("7.4");

    private List<Class<? extends Plugin>> subPlugins() {
        return List.of(
                JavaPlugin.class,
                GroovyPlugin.class,
                CheckstylePlugin.class,
                GitignorePlugin.class
        );
    }

    @Override
    public void apply(Project project) {
        checkGradleVersion();
        subPlugins().forEach(it -> project.getPlugins().apply(it));
    }

    /**
     * Проверка минимальной версии gradle
     */
    static void checkGradleVersion() {
        if (GRADLE_MIN_VERSION.compareTo(GradleVersion.current()) < 0) {
            throw new GradleException("Run `./gradle wrapper --gradle-version=%s` to update."
                    .formatted(GRADLE_MIN_VERSION.getVersion()));
        }
    }
}
