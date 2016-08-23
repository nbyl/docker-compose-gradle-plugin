package com.github.nbyl.gradle.dockercompose

import com.github.nbyl.gradle.dockercompose.tasks.DockerComposeDownloadTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class DockerComposePlugin implements Plugin<Project> {

    private Project project

    private DockerComposeExtension config

    private DockerComposeDownloadTask downloadTask

    @Override
    public void apply(Project project) {
        this.project = project

        this.config = this.project.extensions.create('dockerCompose', DockerComposeExtension, this.project)

        this.downloadTask = project.tasks.create(DockerComposeDownloadTask.NAME, DockerComposeDownloadTask)
        project.afterEvaluate {
            this.downloadTask.setEnabled(this.config.download)
        }
    }
}
