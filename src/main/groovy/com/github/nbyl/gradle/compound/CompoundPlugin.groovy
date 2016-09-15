package com.github.nbyl.gradle.compound

import com.github.nbyl.gradle.compound.tasks.compose.DownloadTask
import org.gradle.api.Plugin
import org.gradle.api.Project

class CompoundPlugin implements Plugin<Project> {

    private Project project

    private CompoundExtension config

    private DownloadTask downloadTask

    @Override
    public void apply(Project project) {
        this.project = project

        this.config = this.project.extensions.create(CompoundExtension.EXTENSION_NAME, CompoundExtension, this.project)

        this.downloadTask = project.tasks.create(DownloadTask.NAME, DownloadTask)
        project.afterEvaluate {
            this.downloadTask.setEnabled(this.config.download)
        }
    }
}
