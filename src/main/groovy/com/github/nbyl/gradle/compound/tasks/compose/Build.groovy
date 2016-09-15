package com.github.nbyl.gradle.compound.tasks.compose

import org.gradle.api.tasks.TaskAction

class Build extends BaseTask {

    @TaskAction
    def run() {
        getRunner()
                .withCommand('build')
                .run()
    }
}
