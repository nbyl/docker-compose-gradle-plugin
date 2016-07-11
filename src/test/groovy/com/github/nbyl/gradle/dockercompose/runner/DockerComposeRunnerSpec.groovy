package com.github.nbyl.gradle.dockercompose.runner

import org.gradle.api.Project
import org.gradle.process.ExecSpec
import spock.lang.Specification

public class DockerComposeRunnerSpec extends Specification {

    def "calling run() issues 'docker-compose up'"() {
        given:
        def project = Mock(Project)
        def execCall = Mock(ExecSpec)

        when:
        new DockerComposeRunner(project: project, command: 'up').run()

        then:
        1 * project.exec(_) >> { Closure closure ->
            closure.delegate = execCall
            closure.resolveStrategy = Closure.DELEGATE_FIRST
            closure.call(execCall)
        }
        1 * execCall.commandLine(['docker-compose', 'up'])
    }
}