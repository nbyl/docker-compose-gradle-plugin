package com.github.nbyl.gradle.compound.tasks

import org.gradle.testfixtures.ProjectBuilder
import spock.lang.Specification

abstract class BaseSpecification extends Specification {
    protected def project

    def setup() {
        project = ProjectBuilder.builder().build();
        project.pluginManager.apply('com.github.nbyl.compound')
    }
}
