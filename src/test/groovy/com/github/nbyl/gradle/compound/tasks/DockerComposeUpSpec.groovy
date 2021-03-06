package com.github.nbyl.gradle.compound.tasks

import com.github.nbyl.gradle.compound.runner.DockerComposeRunner
import com.github.nbyl.gradle.compound.tasks.compose.Up

class DockerComposeUpSpec extends BaseSpecification {

    def "calls DockerComposeRunner with up command"() {
        given:
        def task = project.task(type: Up, "Up", {
        })
        def runner = Mock(DockerComposeRunner)

        when:
        task.runner = runner
        task.run()

        then:
        task instanceof Up
        1 * runner.withProject(_) >> runner
        1 * runner.withCommand('up') >> runner
        1 * runner.withArguments(['-d']) >> runner
        1 * runner.run()
    }

    def "detachMode off removes the -d argument"() {
        given:
        def task = project.task(type: Up, "Up", {
            detachMode false
        })
        def runner = Mock(DockerComposeRunner)

        when:
        task.runner = runner
        task.run()

        then:
        task instanceof Up
        1 * runner.withProject(_) >> runner
        1 * runner.withCommand('up') >> runner
        1 * runner.withArguments([]) >> runner
        1 * runner.run()
    }

    def "setting compose file will pass it to the runner"() {
        given:
        def task = project.task(type: Up, "Up", {
            composeFile 'redis.yml'
        })
        def runner = Mock(DockerComposeRunner)

        when:
        task.runner = runner
        task.run()

        then:
        task instanceof Up
        1 * runner.withProject(_) >> runner
        1 * runner.withComposeFile('redis.yml') >> runner
        1 * runner.withCommand('up') >> runner
        1 * runner.withArguments(['-d']) >> runner
        1 * runner.run()
    }
}
