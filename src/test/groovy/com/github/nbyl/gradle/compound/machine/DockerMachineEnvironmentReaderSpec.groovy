package com.github.nbyl.gradle.compound.machine

import org.gradle.api.Project
import org.gradle.process.ExecSpec

// TODO: this spec must be tested in a integration test
class DockerMachineEnvironmentReaderSpec {//extends Specification {

    def "reads the docker environment variables"() {
        given:
        def project = Mock(Project)
        def execCall = Mock(ExecSpec)

        when:
        def variables = new DockerMachineEnvironmentReader(project: project)
                .readEnvironment('dev')

        then:
        1 * project.exec(_) >> { Closure closure ->
            closure.delegate = execCall
            closure.resolveStrategy = Closure.DELEGATE_FIRST
            closure.call(execCall)
        }
        1 * execCall.commandLine(['docker-machine', 'env', 'dev'])
        1 * execCall.setStandardOutput(_) >> {
            args -> println args
        }
    }
}
