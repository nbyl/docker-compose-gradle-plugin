package com.github.nbyl.gradle.dockercompose.tasks

import org.gradle.api.tasks.TaskAction

class DockerComposeDown extends DockerComposeBaseTask {

    @TaskAction
    def run() {
        getRunner()
                .withCommand('down')
                .run()
    }
}
