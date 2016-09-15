package com.github.nbyl.gradle.compound.tasks

import com.github.nbyl.gradle.compound.machine.DockerMachineEnvironmentReader
import com.github.nbyl.gradle.compound.runner.DockerComposeRunner
import org.gradle.api.DefaultTask

abstract class DockerComposeBaseTask extends DefaultTask {

    def String composeFile

    def String dockerMachineEnvironment

    def DockerComposeRunner runner

    def DockerMachineEnvironmentReader environmentReader

    def getRunner() {
        def DockerComposeRunner runner = (this.runner == null) ? new DockerComposeRunner() : this.runner

        if (composeFile) {
            runner.withComposeFile(composeFile)
        }

        if (dockerMachineEnvironment) {
            def DockerMachineEnvironmentReader environmentReader = (this.environmentReader == null) ? new DockerMachineEnvironmentReader(project: project) : this.environmentReader
            runner.withEnvironmentVariables(environmentReader.readEnvironment(dockerMachineEnvironment))
        }

        return runner.withProject(project)
    }
}
