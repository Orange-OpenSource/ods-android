# Developer guide

## Publication

Artifacts are published on [MavenCentral](https://mvnrepository.com/artifact/com.orange.ods.android).

Please use the `tagRelease` Gradle task in order to publish a new release. This task will push a tag named after the Gradle `version` property available in `gradle.properties`. This in turn will trigger a GitHub Actions workflow that will publish the new release to MavenCentral and GitHub.

## Gradle

End-user just need to add Maven Central to their repositories and update their dependencies with latest version:

```kotlin
repositories {
    mavenCentral()
}
```

```kotlin
dependencies {
    implementation("com.orange.ods.android:ods-lib:0.3.0")
}
```
