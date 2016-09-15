package com.github.nbyl.gradle.compound.tasks

import org.gradle.api.tasks.TaskAction

class DockerComposeUp extends DockerComposeBaseTask {

    def boolean detachMode = true

    @TaskAction
    def run() {
        def arguments = []

        if (detachMode) {
            arguments << '-d'
        }

        getRunner()
                .withCommand('up')
                .withArguments(arguments)
                .run()
    }
}
