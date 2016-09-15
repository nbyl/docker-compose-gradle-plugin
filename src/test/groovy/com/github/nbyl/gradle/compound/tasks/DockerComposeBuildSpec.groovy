package com.github.nbyl.gradle.compound.tasks

import com.github.nbyl.gradle.compound.machine.DockerMachineEnvironmentReader
import com.github.nbyl.gradle.compound.runner.DockerComposeRunner

class DockerComposeBuildSpec extends BaseSpecification {

    def "class DockerComposeRunner with build command"() {
        given:
        def task = project.task(type: DockerComposeBuild, "buildImages", {
        })
        def runner = Mock(DockerComposeRunner)

        when:
        task.runner = runner
        task.run()

        then:
        task instanceof DockerComposeBuild
        1 * runner.withProject(_) >> runner
        1 * runner.withCommand('build') >> runner
        1 * runner.run()
    }

    def "setting compose file will pass it to the runner"() {
        given:
        def task = project.task(type: DockerComposeBuild, "buildImages", {
            composeFile 'redis.yml'
        })
        def runner = Mock(DockerComposeRunner)

        when:
        task.runner = runner
        task.run()

        then:
        task instanceof DockerComposeBuild
        1 * runner.withProject(_) >> runner
        1 * runner.withComposeFile('redis.yml') >> runner
        1 * runner.withCommand('build') >> runner
        1 * runner.run()
    }

    def "setting dockerMachineEnvironment will pass it to the runner"() {
        given:
        def task = project.task(type: DockerComposeBuild, "buildImages", {
            dockerMachineEnvironment 'dev'
        })
        def runner = Mock(DockerComposeRunner)
        def environmentReader = Mock(DockerMachineEnvironmentReader)

        when:
        task.runner = runner
        task.environmentReader = environmentReader
        task.run()

        then:
        task instanceof DockerComposeBuild

        1 * environmentReader.readEnvironment('dev') >> ['DOCKER_HOST': 'tcp://192.168.99.100:2376']

        1 * runner.withProject(_) >> runner
        1 * runner.withEnvironmentVariables(['DOCKER_HOST': 'tcp://192.168.99.100:2376']) >> runner
        1 * runner.withCommand('build') >> runner
        1 * runner.run()
    }
}
