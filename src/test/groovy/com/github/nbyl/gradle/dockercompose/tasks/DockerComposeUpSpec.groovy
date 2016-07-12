package com.github.nbyl.gradle.dockercompose.tasks

import com.github.nbyl.gradle.dockercompose.runner.DockerComposeRunner

class DockerComposeUpSpec extends BaseSpecification {

    def "calls DockerComposeRunner with up command"() {
        given:
        def task = project.task(type: DockerComposeUp, "Up", {
        })
        def runner = Mock(DockerComposeRunner)

        when:
        task.runner = runner
        task.run()

        then:
        task instanceof DockerComposeUp
        1 * runner.withProject(_) >> runner
        1 * runner.withCommand('up') >> runner
        1 * runner.run()
    }
}
