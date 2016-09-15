package com.github.nbyl.gradle.compound.tasks

import com.github.nbyl.gradle.compound.runner.DockerComposeRunner
import com.github.nbyl.gradle.compound.tasks.compose.Down

class DockerComposeDownSpec extends BaseSpecification {

    def "calls DockerComposeRunner with down command"() {
        given:
        def task = project.task(type: Down, "Down", {
        })
        def runner = Mock(DockerComposeRunner)

        when:
        task.runner = runner
        task.run()

        then:
        task instanceof Down
        1 * runner.withProject(_) >> runner
        1 * runner.withCommand('down') >> runner
        1 * runner.run()
    }

    def "setting compose file will pass it to the runner"() {
        given:
        def task = project.task(type: Down, "Down", {
            composeFile 'redis.yml'
        })
        def runner = Mock(DockerComposeRunner)

        when:
        task.runner = runner
        task.run()

        then:
        task instanceof Down
        1 * runner.withProject(_) >> runner
        1 * runner.withComposeFile('redis.yml')
        1 * runner.withCommand('down') >> runner
        1 * runner.run()
    }
}
