package com.github.nbyl.gradle.compound.tasks.compose

import org.gradle.api.tasks.TaskAction

class Down extends BaseTask {

    @TaskAction
    def run() {
        getRunner()
                .withCommand('down')
                .run()
    }
}
