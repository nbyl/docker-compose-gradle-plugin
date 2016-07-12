package com.github.nbyl.gradle.dockercompose.tasks

import org.gradle.api.tasks.TaskAction

class DockerComposeUp extends DockerComposeBaseTask {

    def boolean detachMode = true

    @TaskAction
    def run() {
        def arguments = []

        if (detachMode) {
            arguments << '-d'
        }

        runner
                .withProject(project)
                .withCommand('up')
                .withArguments(arguments)
                .run()
    }
}
