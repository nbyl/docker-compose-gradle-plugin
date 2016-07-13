package com.github.nbyl.gradle.dockercompose.machine

import groovy.util.logging.Slf4j
import org.gradle.api.Project

@Slf4j
class DockerMachineEnvironmentReader {

    def Project project

    def readEnvironment(String name) {
        def ByteArrayOutputStream stdout = new ByteArrayOutputStream()
        stdout.withStream {
            project.exec {
                commandLine 'docker-machine', 'env', name
                standardOutput = stdout
            }

            return parseVariables(stdout.toString())
        }
    }

    def parseVariables(String stdout) {
        def variables = [:]
        stdout.eachLine {
            line ->
                if (line.startsWith("export")) {
                    def index = line.indexOf('=')
                    if (index > 0) {
                        def name = line.substring("export ".length(), index).trim()
                        def value = line.substring(index + 1)
                        variables[name] = parseValue(value)
                    }
                }
        }

        log.info("Found docker-machine environment variables: ${variables}")

        return variables
    }

    def parseValue(String value) {
        if (value.startsWith('"')) {
            value = value.substring(1, value.length())
        }
        if (value.endsWith('"')) {
            value = value.substring(0, value.length() - 1)
        }
        return value
    }
}
