---
layout: detail
title: Getting started
description: Getting started with Orange Design System for Android
---

# Instructions

## 1. Depend on our library

Orange Design System for Android is available through Google's Maven Repository.
To use it:

1.  Open the `build.gradle` file for your application.
2.  Make sure that the `repositories` section includes Google's Maven Repository
    `google()`. For example:

    ```groovy
      allprojects {
        repositories {
          google()
          jcenter()
        }
      }
    ```

3.  Add the library to the `dependencies` section:

    ```groovy
      dependencies {
        // ...
        implementation 'com.orange.ods.xxx:yyy:<version>'
        // ...
      }
    ```

