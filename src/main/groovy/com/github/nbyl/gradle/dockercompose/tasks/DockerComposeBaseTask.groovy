package com.github.nbyl.gradle.dockercompose.tasks

import com.github.nbyl.gradle.dockercompose.runner.DockerComposeRunner
import org.gradle.api.DefaultTask

abstract class DockerComposeBaseTask extends DefaultTask {
    protected def DockerComposeRunner runner = new DockerComposeRunner()
}
