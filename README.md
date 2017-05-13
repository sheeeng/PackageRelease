# Package Plugin

This gradle plugin collects artifacts from one repo and promote them to a new repo as a bundled up zip.

## How

1. Add the gradle plugin to your build.gradle:
```
    plugins {
        id "net.praqma.package" version "1.0.2"
    }
```

2. Add upload folder to build.gradle
```
buildproperties.publishing {
    zip {
        from ('build/resolvedDep/')
    }
}
```

3. Update package.properties
```
    productName=ReleasePackage
    version=1.0.1  
    group=net.praqma
    resolveRepo=libs-release-local
    destinationRepo=promote-release-local
    artifacts=artifacts.txt
```

4. Add your artifacts to a file (artifacts.txt) divided by new line, containing group:name:version
```
    com.gradle:plugin:1.2.3
    net.default.customLib:1.0.+
```
5. Update gradle.properties in your ~/.gradle folder
```
repositoryManagerUsername=username
repositoryManagerPassword=password
repositoryManagerUrl=http://127.0.0.1:8081/repository
```

6. Promote your artifacts with
```
    OSX or LINUX: ./gradlew publish
    WIN : gradlew publish
```
