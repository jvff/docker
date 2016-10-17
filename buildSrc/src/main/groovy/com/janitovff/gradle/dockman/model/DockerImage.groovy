package com.janitovff.gradle.dockman.model

import org.gradle.platform.base.ComponentSpec

public interface DockerImage extends ComponentSpec {
    void from(String image)
    void run(String command)
    void add(String sourceFile, String destinationDirectory)
    void cmd(String command)
    void env(Closure environment)
}
