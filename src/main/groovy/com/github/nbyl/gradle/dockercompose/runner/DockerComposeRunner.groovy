package com.github.nbyl.gradle.dockercompose.runner

import org.gradle.api.Project

class DockerComposeRunner {

    def Project project

    def String command

    def run() {
        def commandLineArgs = ['docker-compose', command]

        project.exec {
            commandLine commandLineArgs
        }
    }
}
