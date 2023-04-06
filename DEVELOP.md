# Developer guide

## Publication

Artifacts are published on [MavenCentral](https://mvnrepository.com/artifact/com.orange.ods.android).

Please use the `tagRelease` Gradle task in order to publish a new release. This task will push a tag named like the Gradle `version` property available in `gradle.properties`. This in turn will trigger a GitHub Actions workflow that will publish the new release to MavenCentral and GitHub.

## Gradle

End-user just need to add Maven Central to their repositories and update their dependencies with latest version:

```groovy
repositories {
    mavenCentral()
}
```

```groovy
dependencies {
    implementation 'com.orange.ods.android:ods-lib:0.12.0'
}
```

## Documentation

Execute the commands below to generate and run the documentation:

1. `git submodule init`
2. `git submodule update`
3. `cd docs`
4. `bundle config set --local path 'vendor/bundle'`
5. `bundle install`
6. `bundle exec jekyll serve --trace --watch --force_polling --livereload --livereload-port 4001`

If you encounter errors during installation and your platform is not listed in the `PLATFORMS` section of `Gemfile.lock`, you can optionally run `bundle platform` to retrieve your platform, then `bundle lock --add-platform <your_platform>` to install specific dependencies for you platform.

Finally, open your browser and go to http://127.0.0.1:4000/ods-android/
