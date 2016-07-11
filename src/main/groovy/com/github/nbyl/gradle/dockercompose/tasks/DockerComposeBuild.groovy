package com.github.nbyl.gradle.dockercompose.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class DockerComposeBuild extends DefaultTask {

    @TaskAction
    def run() {
        println 'Hello World: Build!'
    }
}
