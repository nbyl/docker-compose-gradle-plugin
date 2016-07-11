package com.github.nbyl.gradle.dockercompose.tasks

import com.github.nbyl.gradle.dockercompose.runner.DockerComposeRunner
import spock.lang.Specification

class DockerComposeBuildSpec extends Specification {

    def "class DockerComposeRunner with build command"() {
        given:
        def task = new DockerComposeBuild()
        def runner = Mock(DockerComposeRunner)

        when:
        task.runner = runner
        task.run()

        then:
        1 * runner.withProject(_)
        1 * runner.withCommand('build')
        1 * runner.run()
    }
}
