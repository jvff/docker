package com.janitovff.gradle.dockman.model.internal

import org.gradle.model.Managed

import com.janitovff.gradle.dockman.model.DockerImage

@Managed
public interface DockerImageInternal extends DockerImage {
    List<String> getDockerFileCommands()
    String getTag()
}
