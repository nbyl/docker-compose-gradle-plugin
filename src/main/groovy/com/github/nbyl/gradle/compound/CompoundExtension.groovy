package com.github.nbyl.gradle.compound

import org.gradle.api.Project

class CompoundExtension {

    static final EXTENSION_NAME = 'compound'

    def boolean download = false

    def String binary = 'docker-compose'

    CompoundExtension(Project project) {
        if (download) {
            binary = new File(this.project.buildDir, 'dockerCompose' + File.separator + 'docker-compose').absolutePath
        }
    }

    static CompoundExtension get(Project project) {
        project.extensions.getByName(EXTENSION_NAME)
    }
}
