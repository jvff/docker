package com.janitovff.gradle.dockman.plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

import com.janitovff.gradle.dockman.plugins.internal.DockManImagePlugin

public class DockManPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        project.pluginManager.apply DockManImagePlugin
    }
}
