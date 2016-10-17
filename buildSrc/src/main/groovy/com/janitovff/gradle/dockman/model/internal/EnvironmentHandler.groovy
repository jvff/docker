package com.janitovff.gradle.dockman.model.internal

public class EnvironmentHandler {
    private List<String> dockerFileCommands

    EnvironmentHandler(List<String> dockerFileCommands) {
        this.dockerFileCommands = dockerFileCommands
    }

    def propertyMissing(String name) {
        System.err.println("propertyMissing($name)")
    }

    void setProperty(String name, Object value) {
        dockerFileCommands.add "ENV $name=$value"
    }
}
