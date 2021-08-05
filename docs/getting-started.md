---
layout: detail
title: Getting started
description: Getting started with Orange Design System for Android
---

# Instructions

## 1. Depend on our library

Orange Design System for Android is available through [JitPack](https://jitpack.io/) Maven
Repository. To use it:

1. Open the `build.gradle` file for your application.
2. Make sure that the `repositories` section includes JitPack Maven Repository
   `maven { url 'https://jitpack.io' }`. For example:

   ```groovy
     allprojects {
       repositories {
         google()
         maven { url 'https://jitpack.io' }
       }
     }
   ```

3. Add the library to the `dependencies` section:

   ```groovy
     dependencies {
       // ...
       implementation 'com.github.ods-sandbox:android:v0.0.1-alpha'
       // ...
     }
   ```

### 2. Compile your app with Android 11

Orange Design System library depends on Material Design library from Google. For this reason, you
will have to install Android Studio 4.0 or higher to build with Android 11, and update your
app's `compileSdkVersion` to `30`.

### 3. Ensure you are using `AppCompatActivity`

Using `AppCompatActivity` will ensure that all the components work correctly. If you are unable to
extend from `AppCompatActivity`, update your activities to use
`AppCompatDelegate`. This will enable the `AppCompat` versions of components to be inflated among
other important things.

### 4. Change your app theme to inherit from a Orange Design theme

Update your app theme to inherit from Orange theme, e.g.:

```xml
<style name="Theme.MyApp" parent="Theme.Orange">
    <!-- ... -->
</style>
```

Orange theme supports both light and dark mode.

### 5. Preload fonts used by Orange Theme

Orange theme for Android uses [downloadable fonts](https://developer.android.com/guide/topics/ui/look-and-feel/downloadable-fonts)

In order to preload fonts on application start you have to declare a `meta-data` in your `AndroidManifest.xml`
```xml
<meta-data
    android:name="preloaded_fonts"
    android:resource="@array/preloaded_fonts" />
```
