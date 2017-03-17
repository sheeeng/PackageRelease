package net.praqma

import groovy.transform.CompileDynamic
import groovy.transform.CompileStatic
import org.apache.tools.ant.taskdefs.condition.Os
import org.gradle.api.*
import java.io.File

import org.gradle.api.tasks.Copy
import org.gradle.api.tasks.Sync
import org.gradle.api.tasks.diagnostics.DependencyReportTask;

@CompileDynamic
class PackagePlugin implements Plugin<Project> {

    private Project project

    @Override
    void apply(Project project) {
        this.project = project

        project.apply(plugin: 'base')
        project.apply(plugin: 'maven-publish')

        BuildExtension extension = project.extensions.create('buildproperties', BuildExtension, project) as BuildExtension

        File packageFile = project.file('package.properties')

        if (!packageFile.exists()) {
            throw new GradleException("No package properties file found. File expected: ${packageFile}")
        }
        packageFile.withInputStream {
            parseBuildDefinition(it, extension)
        }

        defineRepositoryForDependencies(extension)

        handleDependencies(project)

        createTasks(extension)

        setupPublishing(extension)
    }

    /**
      * Parse build definition, store info in the extension'
      **/
    private void parseBuildDefinition(InputStream input, BuildExtension buildExtension) {
        Properties props = new Properties()
        props.load(input)
        def deps = []
        new File(props.artifacts).eachLine(){
            deps << it
        }
        buildExtension.with {
            group = props.group
            version = props.version
            buildWorkingDir = project.projectDir
            destinationRepo = props.destinationRepo ?: 'net.default'
            resolveRepo = props.resolveRepo
            dependencies = deps
            productName = props.productName ?: 'demo_project'
        }
        project.version = buildExtension.version
        project.group = buildExtension.group

        /*
        project.gradle.projectsEvaluated {
            project.configurations.all { Configuration c -> c.resolutionStrategy.cacheChangingModulesFor 0, 'seconds' }
        }
        */
    }

    @CompileDynamic
    private void defineRepositoryForDependencies(BuildExtension buildExtension) {
        String contextUrl = project.ext.properties.repositoryManagerUrl
        project.repositories {
            println "Artifact storage URL   ==============    : ${contextUrl}"
            if(contextUrl) {
                maven {
                    url contextUrl + '/'+ buildExtension.resolveRepo
                }
            }
            mavenLocal()}
    }

    @CompileDynamic
    private void handleDependencies(Project project) {
        project.configurations.create('_lib')
        project.dependencies {
            project.buildproperties.dependencies.each {
                _lib it
            }
        }
    }

    @CompileDynamic
    private void createTasks(BuildExtension buildExtension) {

        Sync t1 = createBuildTask('resolveDependencies', Sync) {
            project.configurations._lib.collect {
                String name = it.getName().split('-')[0]
                from (it){
                    into name
                }
            }
            into ("${project.buildDir}/resolvedDep")
        }

        Task t2 = createBuildTask('listDeps', DependencyReportTask) {
            dependsOn t1
            Set configs = [project.configurations._lib]
            setConfigurations(configs)
            File file = new File('package.txt')
            outputFile = file
        }

        Task t3 = createBuildTask('copyDepFile', Copy) {
            dependsOn t2
            from('package.txt')
            into "${project.buildDir}/resolvedDep"
        }
    }

    @CompileDynamic
    void setupPublishing(BuildExtension extension) {
        String repoUser = project.ext.properties.repositoryManagerUsername
        String repoPassword = project.ext.properties.repositoryManagerPassword
        String contextUrl = project.ext.properties.repositoryManagerUrl
        String destinationRepo = project.buildproperties.destinationRepo
        if (repoUser == null || repoPassword == null) {
            project.logger.lifecycle "Incomplete credentials for artifact repository. No publishing"
        } else {
            String repoUrl = "${contextUrl}"+'/'+"${destinationRepo}"
            project.publishing.repositories {
                maven {
                    url repoUrl
                    credentials {
                        username repoUser
                        password repoPassword
                    }
                }
            }
        }
    }

    public <T extends Task> T createBuildTask(String name, Class<T> type, Closure closure) {
        Task t = project.tasks.create(name: name, type: type, group: 'BuildProperties', closure)
        return t
    }
}
