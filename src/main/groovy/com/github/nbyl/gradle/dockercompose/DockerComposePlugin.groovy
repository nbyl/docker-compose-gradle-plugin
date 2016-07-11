package com.github.nbyl.gradle.dockercompose;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

class DockerComposePlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.extensions.add('dockerCompose', DockerComposeExtension)
    }
}
