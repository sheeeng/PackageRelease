package net.praqma

import org.gradle.api.GradleException
import org.gradle.api.Project

class BuildExtension {

    Project project

    BuildExtension(Project project) {
        this.project = project
    }
    String version
    String group
    String productName
    String destinationRepo
    String resolveRepo

    File buildWorkingDir

    Collection<String> dependencies

    final BuildPublishing publishing = new BuildPublishing(this)

    BuildPublishing publishing(Closure closure) {
        publishing.with(closure)
        publishing
    }


}
