package com.github.nbyl.gradle.compound.tasks

import org.gradle.api.tasks.TaskAction

class DockerComposeBuild extends DockerComposeBaseTask {

    @TaskAction
    def run() {
        getRunner()
                .withCommand('build')
                .run()
    }
}
