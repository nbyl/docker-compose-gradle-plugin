package com.github.nbyl.gradle.dockercompose.tasks

import com.github.nbyl.gradle.dockercompose.runner.DockerComposeRunner
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.TaskAction

class DockerComposeBuild extends DefaultTask {

    def DockerComposeRunner runner = new DockerComposeRunner()

    @TaskAction
    def run() {
        runner
                .withProject(project)
                .withCommand('build')
                .run()
    }
}
