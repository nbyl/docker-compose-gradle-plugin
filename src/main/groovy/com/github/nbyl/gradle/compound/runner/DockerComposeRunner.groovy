package com.github.nbyl.gradle.compound.runner

import com.github.nbyl.gradle.compound.DockerComposeExtension
import org.gradle.api.Project

class DockerComposeRunner {

    def Project project

    def String command

    def String composeFile

    def arguments

    def environmentVariables

    def withProject(Project project) {
        this.project = project
        return this
    }

    def withCommand(String command) {
        this.command = command
        return this
    }

    def withComposeFile(String composeFile) {
        this.composeFile = composeFile
        return this
    }

    def withArguments(arguments) {
        this.arguments = arguments
        return this
    }

    def withEnvironmentVariables(environmentVariables) {
        this.environmentVariables = environmentVariables
        return this
    }

    def run() {
        def DockerComposeExtension extension = DockerComposeExtension.get(project)
        def commandLineArgs = [extension.binary]

        if (composeFile) {
            commandLineArgs << '-f'
            commandLineArgs << composeFile
        }

        if (System.properties['docker.compose.verbose']) {
            commandLineArgs << '--verbose'
        }

        commandLineArgs << command

        if (arguments) {
            commandLineArgs = commandLineArgs + arguments
        }

        if (environmentVariables) {
            project.exec {
                commandLine commandLineArgs
                environment environmentVariables
            }
        } else {
            project.exec {
                commandLine commandLineArgs
            }
        }
    }
}
