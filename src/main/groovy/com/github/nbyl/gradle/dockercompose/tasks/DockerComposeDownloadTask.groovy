package com.github.nbyl.gradle.dockercompose.tasks

import groovy.util.logging.Slf4j
import org.gradle.api.internal.AbstractTask
import org.gradle.api.tasks.TaskAction

import java.nio.file.Files
import java.nio.file.attribute.PosixFilePermission

@Slf4j
public class DockerComposeDownloadTask extends AbstractTask {

    public final static String NAME = 'dockerComposeDownload'

    public DockerComposeDownloadTask() {
        this.project.afterEvaluate {
            getOutputs().file(target())
        }
    }

    @TaskAction
    public downloadDockerCompose() {
        def targetFile = target()
        def url = "https://github.com/docker/compose/releases/download/1.8.0/docker-compose-${getKernelName()}-${getMachine()}"

        log.info("Downloading ${url} to ${targetFile.getAbsolutePath()}")

        target().getParentFile().mkdirs()

        target().withOutputStream {
            out ->
                new URL(url).withInputStream {
                    input ->
                        out << input
                }
        }


        Files.setPosixFilePermissions(targetFile.toPath(),
                [PosixFilePermission.OWNER_EXECUTE, PosixFilePermission.OWNER_READ, PosixFilePermission.OWNER_WRITE] as Set)
    }

    File target() {
        new File(this.project.getBuildDir(), 'dockerCompose' + File.separator + 'docker-compose')
    }

    String getKernelName() {
        return 'Linux'
    }

    String getMachine() {
        return 'x86_64'
    }
}
