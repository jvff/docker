package com.janitovff.gradle.dockman.plugins.internal

import org.gradle.api.Task
import org.gradle.model.ModelMap
import org.gradle.model.Mutate
import org.gradle.model.Path
import org.gradle.model.RuleSource
import org.gradle.platform.base.ComponentType
import org.gradle.platform.base.TypeBuilder

import com.janitovff.gradle.dockman.model.DockerImage
import com.janitovff.gradle.dockman.model.internal.DefaultDockerImage
import com.janitovff.gradle.dockman.model.internal.DockerImageInternal
import com.janitovff.gradle.dockman.tasks.CreateDockerImage

public class DockManImagePlugin extends RuleSource {
    @ComponentType
    public void registerDockerImageType(TypeBuilder<DockerImage> builder) {
        builder.defaultImplementation DefaultDockerImage
        builder.internalView DockerImageInternal
    }

    @Mutate
    public void addImageCreationTasks(@Path("tasks") ModelMap<Task> tasks,
            @Path("buildDir") File buildDir, ModelMap<DockerImage> images) {
        images.each { image ->
            tasks.create("createImage${image.name.capitalize()}",
                    CreateDockerImage) { task ->
                def outputSubDirectory = "docker/images/$image.name"

                task.setComponent image
                task.outputDirectory = new File(buildDir, outputSubDirectory)
            }
        }
    }
}
