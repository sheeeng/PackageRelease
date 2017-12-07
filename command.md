# Commands

These are prototyping steps for artifact promotion only. There is no continuous versioning here.

Note: These steps assume Jenkins has continuous versioning in place with `Jenkinsfile`.

* Checkout `git@github.com:sheeeng/gildedrose.git` or `https://github.com/sheeeng/gildedrose.git` repository.

* Run Nexus 3 in local Docker environment.

```
mkdir -p /opt/containers/nexus3/nexus-data; \
sudo chown -R 200 /opt/containers/nexus3/nexus-data;

docker pull sonatype/nexus3; \
docker rm -fv nexus-three; \
docker run \
--name nexus-three \
--publish 8081:8081 \
--env NEXUS_CONTEXT=nexus3 \
--volume /opt/containers/nexus3/nexus-data:/nexus-data \
sonatype/nexus3
```

* Create relevant Nexus repositories.

For example, `sponge-alpha`, `sponge-beta`, etc. See the map in `build.gradle` for more information.

* Verify you `settings.xml` has the below content.

```
cat ~/.m2/settings.xml 
<?xml version="1.0"?>
<settings>
  <servers>
    <server>
      <id>workshop-gildedrose</id>
      <username>admin</username>
      <password>{0LMeW1h2uPIHaki14o07gyafNHzc/4h0f5eWo9wy2Mk=}</password>
    </server>
  </servers>
  <profiles>
    <profile>
      <id>workshop-gildedrose</id>
      <activation>
        <activeByDefault>true</activeByDefault>
      </activation>
      <repositories>
        <repository>
          <id>central</id>
	  <url>http://localhost:8081/nexus3/repository/maven-central</url>
        </repository>
        <repository>
          <id>sponge-alpha</id>
          <url>http://localhost:8081/nexus3/repository/sponge-alpha</url>
        </repository>
      </repositories>
    </profile>
  </profiles>
</settings>
```

* Use following commands inside `gildedrose` repository.

```
mvn install
mvn site
mvn deploy
```

You would have an artifact stored in Nexus under `sponge-alpha` repository.

* Verify that you `gradle.properties` has below content. Use the correct password.

```
$ cat ~/.gradle/gradle.properties 
mavenUser=admin
mavenPassword=mavenPassword
```

Note: This file cannot use the encrypted password from Maven context.

* Run the below command to promote single artifact.

```
$ ./gradlew publish -P targetEnvironment=beta -P pomFile=../gildedrose/pom.xml
Variables Map:
* targetEnvironment: beta
* to: ../gildedrose/pom.xml

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

A failed example for promoting an artifact that did not exist in the source repository map.

```
$ ./gradlew publish -P targetEnvironment=delta -P pomFile=../gildedrose/pom.xml
Variables Map:
* targetEnvironment: delta
* to: ../gildedrose/pom.xml

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
