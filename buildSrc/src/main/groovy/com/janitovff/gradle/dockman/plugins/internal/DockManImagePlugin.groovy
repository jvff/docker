package com.janitovff.gradle.dockman.plugins.internal

import org.gradle.api.internal.resolve.ProjectModelResolver
import org.gradle.api.Task
import org.gradle.api.UnknownProjectException
import org.gradle.internal.service.ServiceRegistry
import org.gradle.model.Each
import org.gradle.model.internal.registry.ModelRegistry
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

    @Mutate
    public void addDependencyToBaseImage(@Each CreateDockerImage task,
            ServiceRegistry serviceRegistry) {
        try {
            def project = getBaseImageProject(task.baseImage, serviceRegistry)

            addDependencyToBaseImageTask(task, project)
        } catch (UnknownProjectException exception) {
            // No base image task to depend on
        }
    }

    private ModelRegistry getBaseImageProject(String baseImage,
            ServiceRegistry serviceRegistry) {
        def resolver = serviceRegistry.get(ProjectModelResolver)
        def baseImageProject = ":" + baseImage.replaceAll("/", ":")

        return resolver.resolveProjectModel(baseImageProject)
    }

    private void addDependencyToBaseImageTask(CreateDockerImage task,
            ModelRegistry projectModel) {
        def baseImageTask = getBaseImageTask(task.baseImage, projectModel)

        if (baseImageTask != null)
            task.dependsOn baseImageTask
    }

    private getBaseImageTask(String baseImage, ModelRegistry projectModel) {
        def baseImageName = baseImage.split("/").last().capitalize()
        def tasks = projectModel.find("tasks", Object)

        return tasks.find { task -> task.name == "createImage$baseImageName" }
    }
}
