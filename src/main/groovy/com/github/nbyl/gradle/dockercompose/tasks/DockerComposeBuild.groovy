package com.github.nbyl.gradle.dockercompose.tasks

import org.gradle.api.tasks.TaskAction

class DockerComposeBuild extends DockerComposeBaseTask {

    @TaskAction
    def run() {
        runner
                .withProject(project)
                .withCommand('build')
                .run()
    }
}
