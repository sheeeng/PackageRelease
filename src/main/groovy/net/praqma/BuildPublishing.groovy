package net.praqma

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import org.gradle.api.Project
import org.gradle.api.Task
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.api.tasks.bundling.Zip


@CompileStatic
class BuildPublishing {

    private final BuildExtension extension

    BuildPublishing(BuildExtension extension) {
        this.extension = extension
    }

    @CompileDynamic
    void zip(Closure closure) {
        String pubName = 'publication'
        Project project = extension.project

        Task createDepFile = project.listDeps
        Task executeBuildTask = project.resolveDependencies

        Task taskZip = project.tasks.create(name: "moveDependencies", type: Zip) {
            dependsOn executeBuildTask
            from createDepFile
            with project.copySpec(closure)
        }

        project.publishing.publications {
                "${pubName}"(MavenPublication) {
                    groupId project.group
                    version project.version
                    artifactId extension.productName

                    artifact taskZip
                }
        }
    }
}
