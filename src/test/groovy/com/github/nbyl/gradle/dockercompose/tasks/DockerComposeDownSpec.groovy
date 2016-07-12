package com.github.nbyl.gradle.dockercompose.tasks

import com.github.nbyl.gradle.dockercompose.runner.DockerComposeRunner

class DockerComposeDownSpec extends BaseSpecification {

    def "calls DockerComposeRunner with down command"() {
        given:
        def task = project.task(type: DockerComposeDown, "Down", {
        })
        def runner = Mock(DockerComposeRunner)

        when:
        task.runner = runner
        task.run()

        then:
        task instanceof DockerComposeDown
        1 * runner.withProject(_) >> runner
        1 * runner.withCommand('down') >> runner
        1 * runner.run()
    }
}
