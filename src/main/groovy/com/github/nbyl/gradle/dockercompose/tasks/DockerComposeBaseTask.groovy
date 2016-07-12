package com.github.nbyl.gradle.dockercompose.tasks

import com.github.nbyl.gradle.dockercompose.runner.DockerComposeRunner
import org.gradle.api.DefaultTask

abstract class DockerComposeBaseTask extends DefaultTask {

    def String composeFile

    def DockerComposeRunner runner

    def getRunner() {
        def DockerComposeRunner runner = (this.runner == null) ? new DockerComposeRunner() : this.runner

        if (composeFile) {
            runner.withComposeFile(composeFile)
        }

        return runner.withProject(project)
    }
}
