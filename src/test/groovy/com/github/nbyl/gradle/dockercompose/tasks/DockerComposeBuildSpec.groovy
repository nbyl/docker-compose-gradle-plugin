package com.github.nbyl.gradle.dockercompose.tasks

import com.github.nbyl.gradle.dockercompose.runner.DockerComposeRunner

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
}
