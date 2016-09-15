package com.github.nbyl.gradle.compound.tasks

import com.github.nbyl.gradle.compound.runner.DockerComposeRunner

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

    def "setting compose file will pass it to the runner"() {
        given:
        def task = project.task(type: DockerComposeDown, "Down", {
            composeFile 'redis.yml'
        })
        def runner = Mock(DockerComposeRunner)

        when:
        task.runner = runner
        task.run()

        then:
        task instanceof DockerComposeDown
        1 * runner.withProject(_) >> runner
        1 * runner.withComposeFile('redis.yml')
        1 * runner.withCommand('down') >> runner
        1 * runner.run()
    }
}
