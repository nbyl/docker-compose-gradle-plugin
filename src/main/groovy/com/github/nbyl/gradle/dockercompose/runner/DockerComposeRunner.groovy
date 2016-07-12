package com.github.nbyl.gradle.dockercompose.runner

import org.gradle.api.Project

class DockerComposeRunner {

    def Project project

    def String command

    def withProject(Project project) {
        this.project = project
        return this
    }

    def withCommand(String command) {
        this.command = command
        return this
    }

    def run() {
        def commandLineArgs = ['docker-compose', command]

        project.exec {
            commandLine commandLineArgs
        }
    }
}
