package com.github.nbyl.gradle.dockercompose

import org.gradle.api.Project

class DockerComposeExtension {

    static final EXTENSION_NAME = 'dockerCompose'

    def boolean download = false

    def String binary = 'docker-compose'

    DockerComposeExtension(Project project) {
        if (download) {
            binary = new File(this.project.buildDir, 'dockerCompose' + File.separator + 'docker-compose').absolutePath
        }
    }

    static DockerComposeExtension get(Project project) {
        project.extensions.getByName(EXTENSION_NAME)
    }
}
