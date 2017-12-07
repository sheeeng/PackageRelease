# Commands

```
$ ./gradlew publish -P targetEnvironment=beta -P pomFile=../gilded-rose/pom.xml
Variables Map:
* targetEnvironment: beta
* to: ../gilded-rose/pom.xml

Artifact dependency from pomfile is net.praqma.codeacademy:gildedrose:0.0.2-2017.12.07T09.31.08.855-54ec33a9801d8974e0f300c8b1a02ee5fd240b48
:copyDependencies
:generatePomFileForMavenJavaPublication
:publishMavenJavaPublicationToMavenRepository
Upload http://localhost:8081/nexus3/repository/sponge-beta/net/praqma/codeacademy/gildedrose/0.0.2-2017.12.07T09.31.08.855-54ec33a9801d8974e0f300c8b1a02ee5fd240b48/gildedrose-0.0.2-2017.12.07T09.31.08.855-54ec33a9801d8974e0f300c8b1a02ee5fd240b48.jar
Upload http://localhost:8081/nexus3/repository/sponge-beta/net/praqma/codeacademy/gildedrose/0.0.2-2017.12.07T09.31.08.855-54ec33a9801d8974e0f300c8b1a02ee5fd240b48/gildedrose-0.0.2-2017.12.07T09.31.08.855-54ec33a9801d8974e0f300c8b1a02ee5fd240b48.jar.sha1
Upload http://localhost:8081/nexus3/repository/sponge-beta/net/praqma/codeacademy/gildedrose/0.0.2-2017.12.07T09.31.08.855-54ec33a9801d8974e0f300c8b1a02ee5fd240b48/gildedrose-0.0.2-2017.12.07T09.31.08.855-54ec33a9801d8974e0f300c8b1a02ee5fd240b48.jar.md5
Upload http://localhost:8081/nexus3/repository/sponge-beta/net/praqma/codeacademy/gildedrose/0.0.2-2017.12.07T09.31.08.855-54ec33a9801d8974e0f300c8b1a02ee5fd240b48/gildedrose-0.0.2-2017.12.07T09.31.08.855-54ec33a9801d8974e0f300c8b1a02ee5fd240b48.pom
Upload http://localhost:8081/nexus3/repository/sponge-beta/net/praqma/codeacademy/gildedrose/0.0.2-2017.12.07T09.31.08.855-54ec33a9801d8974e0f300c8b1a02ee5fd240b48/gildedrose-0.0.2-2017.12.07T09.31.08.855-54ec33a9801d8974e0f300c8b1a02ee5fd240b48.pom.sha1
Upload http://localhost:8081/nexus3/repository/sponge-beta/net/praqma/codeacademy/gildedrose/0.0.2-2017.12.07T09.31.08.855-54ec33a9801d8974e0f300c8b1a02ee5fd240b48/gildedrose-0.0.2-2017.12.07T09.31.08.855-54ec33a9801d8974e0f300c8b1a02ee5fd240b48.pom.md5
Could not find metadata net.praqma.codeacademy:gildedrose/maven-metadata.xml in remote (http://localhost:8081/nexus3/repository/sponge-beta/)
Upload http://localhost:8081/nexus3/repository/sponge-beta/net/praqma/codeacademy/gildedrose/maven-metadata.xml
Upload http://localhost:8081/nexus3/repository/sponge-beta/net/praqma/codeacademy/gildedrose/maven-metadata.xml.sha1
Upload http://localhost:8081/nexus3/repository/sponge-beta/net/praqma/codeacademy/gildedrose/maven-metadata.xml.md5
:publish

BUILD SUCCESSFUL

Total time: 1.221 secs
```

```
$ ./gradlew publish -P targetEnvironment=delta -P pomFile=../gilded-rose/pom.xml
Variables Map:
* targetEnvironment: delta
* to: ../gilded-rose/pom.xml

Artifact dependency from pomfile is net.praqma.codeacademy:gildedrose:0.0.2-2017.12.07T09.31.08.855-54ec33a9801d8974e0f300c8b1a02ee5fd240b48
:copyDependencies FAILED

FAILURE: Build failed with an exception.

* What went wrong:
Could not resolve all dependencies for configuration ':compile'.
> Could not find net.praqma.codeacademy:gildedrose:0.0.2-2017.12.07T09.31.08.855-54ec33a9801d8974e0f300c8b1a02ee5fd240b48.
  Searched in the following locations:
      http://localhost:8081/nexus3/repository/sponge-gamma/net/praqma/codeacademy/gildedrose/0.0.2-2017.12.07T09.31.08.855-54ec33a9801d8974e0f300c8b1a02ee5fd240b48/gildedrose-0.0.2-2017.12.07T09.31.08.855-54ec33a9801d8974e0f300c8b1a02ee5fd240b48.pom
      http://localhost:8081/nexus3/repository/sponge-gamma/net/praqma/codeacademy/gildedrose/0.0.2-2017.12.07T09.31.08.855-54ec33a9801d8974e0f300c8b1a02ee5fd240b48/gildedrose-0.0.2-2017.12.07T09.31.08.855-54ec33a9801d8974e0f300c8b1a02ee5fd240b48.jar
  Required by:
      project :

* Try:
Run with --stacktrace option to get the stack trace. Run with --info or --debug option to get more log output.

BUILD FAILED

Total time: 0.896 secs
```