package com.github.nbyl.gradle.compound.runner

import com.github.nbyl.gradle.compound.DockerComposeExtension
import org.gradle.api.Project
import org.gradle.api.plugins.ExtensionContainer
import org.gradle.process.ExecSpec
import spock.lang.Specification

public class DockerComposeRunnerSpec extends Specification {

    def "calling run() issues 'docker-compose up'"() {
        given:
        def project = Mock(Project)
        def extensionContainer = Mock(ExtensionContainer)
        def extension = Mock(DockerComposeExtension)
        def execCall = Mock(ExecSpec)

        when:
        new DockerComposeRunner()
                .withProject(project)
                .withCommand('up')
                .run()

        then:
        1 * project.extensions >> extensionContainer
        1 * extensionContainer.getByName('dockerCompose') >> extension
        1 * extension.binary >> 'docker-compose'
        1 * project.exec(_) >> { Closure closure ->
            closure.delegate = execCall
            closure.resolveStrategy = Closure.DELEGATE_FIRST
            closure.call(execCall)
        }
        1 * execCall.commandLine(['docker-compose', 'up'])
    }

    def "adding arguments passes these to the final command"() {
        given:
        def project = Mock(Project)
        def extensionContainer = Mock(ExtensionContainer)
        def extension = Mock(DockerComposeExtension)
        def execCall = Mock(ExecSpec)

        when:
        new DockerComposeRunner()
                .withProject(project)
                .withCommand('up')
                .withArguments(['-d'])
                .run()

        then:
        1 * project.extensions >> extensionContainer
        1 * extensionContainer.getByName('dockerCompose') >> extension
        1 * extension.binary >> 'docker-compose'
        1 * project.exec(_) >> { Closure closure ->
            closure.delegate = execCall
            closure.resolveStrategy = Closure.DELEGATE_FIRST
            closure.call(execCall)
        }
        1 * execCall.commandLine(['docker-compose', 'up', '-d'])
    }

    def "compose file is passed to the command line"() {
        given:
        def project = Mock(Project)
        def extensionContainer = Mock(ExtensionContainer)
        def extension = Mock(DockerComposeExtension)
        def execCall = Mock(ExecSpec)

        when:
        new DockerComposeRunner()
                .withProject(project)
                .withComposeFile('redis.yml')
                .withCommand('up')
                .run()

        then:
        1 * project.extensions >> extensionContainer
        1 * extensionContainer.getByName('dockerCompose') >> extension
        1 * extension.binary >> 'docker-compose'
        1 * project.exec(_) >> { Closure closure ->
            closure.delegate = execCall
            closure.resolveStrategy = Closure.DELEGATE_FIRST
            closure.call(execCall)
        }
        1 * execCall.commandLine(['docker-compose', '-f', 'redis.yml', 'up'])
    }

    def "uses downloaded binary"() {
        given:
        def project = Mock(Project)
        def extensionContainer = Mock(ExtensionContainer)
        def extension = Mock(DockerComposeExtension)
        def execCall = Mock(ExecSpec)

        when:
        new DockerComposeRunner()
                .withProject(project)
                .withCommand('up')
                .run()

        then:
        1 * project.extensions >> extensionContainer
        1 * extensionContainer.getByName('dockerCompose') >> extension
        1 * extension.binary >> new File('.', 'dockerCompose' + File.separator + 'docker-compose').absolutePath
        1 * project.exec(_) >> { Closure closure ->
            closure.delegate = execCall
            closure.resolveStrategy = Closure.DELEGATE_FIRST
            closure.call(execCall)
        }
        1 * execCall.commandLine([new File('.', 'dockerCompose' + File.separator + 'docker-compose').absolutePath, 'up'])
    }
}