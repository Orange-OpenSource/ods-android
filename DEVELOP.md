# Developer guide

## Publication
We use JitPack (https://jitpack.io/) for publication, so we just have to create a release in Github (and add a git tag) to make a specific version available.

End-user just need to set
```groovy
maven { url 'https://jitpack.io' }
```
in the `allprojects/repositories` section of their main build.gradle file.

Then they can use:
- a specific version:
```groovy
dependencies {
    ... 
    implementation 'com.github.Orange-OpenSource:ods-android:0.0.1'
}
```

- or a snapshot version (latest commit from a specific branch):
```groovy
dependencies {
    ... 
    implementation 'com.github.Orange-OpenSource:ods-android:develop-SNAPSHOT'
}
```