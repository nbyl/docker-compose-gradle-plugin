package com.github.nbyl.gradle.dockercompose

import org.gradle.api.Project

class DockerComposeExtension {

    static final EXTENSION_NAME = 'dockerCompose'

    def boolean download = false

    DockerComposeExtension(Project project) {}

    static DockerComposeExtension get(Project project) {
        project.extensions.getByName(EXTENSION_NAME)
    }
}
