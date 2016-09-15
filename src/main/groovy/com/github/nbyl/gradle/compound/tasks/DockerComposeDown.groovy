package com.github.nbyl.gradle.compound.tasks

import org.gradle.api.tasks.TaskAction

class DockerComposeDown extends DockerComposeBaseTask {

    @TaskAction
    def run() {
        getRunner()
                .withCommand('down')
                .run()
    }
}
