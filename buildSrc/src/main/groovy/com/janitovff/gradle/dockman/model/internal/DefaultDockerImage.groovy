package com.janitovff.gradle.dockman.model.internal

import org.gradle.platform.base.component.internal.DefaultComponentSpec

import static groovy.lang.Closure.DELEGATE_ONLY

public class DefaultDockerImage extends DefaultComponentSpec
        implements DockerImageInternal {
    private String baseImage
    private LinkedList<String> dockerFileCommands = new LinkedList<String>()
    private LinkedList<File> requestedFiles = new LinkedList<File>()

    void from(String baseImage) {
        this.baseImage = baseImage

        dockerFileCommands.add "FROM $baseImage"
    }

    void run(String command) {
        dockerFileCommands.add "RUN $command"
    }

    void add(String sourceFile, String destinationDirectory) {
        requestedFiles.add new File(sourceFile)
        dockerFileCommands.add "ADD $sourceFile $destinationDirectory"
    }

    void cmd(String command) {
        dockerFileCommands.add "CMD $command"
    }

    void env(Closure environment) {
        environment.delegate = new EnvironmentHandler(dockerFileCommands)
        environment.resolveStrategy = DELEGATE_ONLY
        environment.call()
    }

    List<String> getDockerFileCommands() {
        return dockerFileCommands
    }

    String getTag() {
        return "$projectPath"
            .replaceAll(":", "/")
            .substring(1)
    }
}
