package com.github.nbyl.gradle.compound.tasks.compose

import org.gradle.api.tasks.TaskAction

class Up extends BaseTask {

    def boolean detachMode = true

    @TaskAction
    def run() {
        def arguments = []

        if (detachMode) {
            arguments << '-d'
        }

        getRunner()
                .withCommand('up')
                .withArguments(arguments)
                .run()
    }
}
