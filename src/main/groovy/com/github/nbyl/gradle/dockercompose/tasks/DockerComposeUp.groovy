package com.github.nbyl.gradle.dockercompose.tasks

class DockerComposeUp extends DockerComposeBaseTask {

    def run() {
        runner
                .withProject(project)
                .withCommand('up')
                .run()
    }
}
