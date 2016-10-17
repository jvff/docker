package com.janitovff.gradle.dockman.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputDirectory
import org.gradle.api.tasks.OutputFile
import org.gradle.api.tasks.TaskAction

import com.github.dockerjava.api.DockerClient
import com.github.dockerjava.api.model.BuildResponseItem
import com.github.dockerjava.core.command.BuildImageResultCallback
import com.github.dockerjava.core.DefaultDockerClientConfig
import com.github.dockerjava.core.DockerClientBuilder
import com.github.dockerjava.core.DockerClientConfig

import com.janitovff.gradle.dockman.model.internal.DockerImageInternal

import static com.github.dockerjava.core.RemoteApiVersion.VERSION_1_23

public class CreateDockerImage extends DefaultTask {
    @Input
    List<String> dockerFileCommands

    @InputDirectory
    File projectDirectory = project.projectDir

    @Input
    String tag

    @OutputFile
    File dockerFile

    public void setComponent(DockerImageInternal component) {
        dockerFileCommands = component.dockerFileCommands
        tag = component.tag
    }

    public void setOutputDirectory(File outputDirectory) {
        dockerFile = new File(outputDirectory, "Dockerfile")
    }

    @TaskAction
    void buildImage() {
        createDockerfile()
        createImage()
    }

    private void createDockerfile() {
        dockerFile.withWriter { writer ->
            dockerFileCommands.each { line ->
                writer.println(line)
            }
        }
    }

    private void createImage() {
        createDockerClient()
            .buildImageCmd()
            .withBaseDirectory(projectDirectory)
            .withTag(tag)
            .withDockerfile(dockerFile)
            .exec(imageCreationListener)
            .awaitImageId()
    }

    private DockerClient createDockerClient() {
        DockerClientConfig dockerConfig = DefaultDockerClientConfig
            .createDefaultConfigBuilder()
            .withDockerHost("unix:///var/run/docker.sock")
            .withApiVersion(VERSION_1_23)
            .build()

        return DockerClientBuilder.getInstance(dockerConfig).build()
    }

    private BuildImageResultCallback imageCreationListener =
            new BuildImageResultCallback() {
        @Override
        public void onNext(BuildResponseItem item) {
            System.err.println("${item.stream?.trim()}")

            super.onNext(item)
        }
    }
}
