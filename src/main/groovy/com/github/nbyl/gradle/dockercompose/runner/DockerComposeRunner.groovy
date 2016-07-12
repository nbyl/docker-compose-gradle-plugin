package com.github.nbyl.gradle.dockercompose.runner

import org.gradle.api.Project

class DockerComposeRunner {

    def Project project

    def String command

    def arguments

    def withProject(Project project) {
        this.project = project
        return this
    }

    def withCommand(String command) {
        this.command = command
        return this
    }

    def withArguments(arguments) {
        this.arguments = arguments
        return this
    }

    def run() {
        def commandLineArgs = ['docker-compose', command]

        if (arguments) {
            commandLineArgs = commandLineArgs + arguments
        }

        project.exec {
            commandLine commandLineArgs
        }
    }
}
