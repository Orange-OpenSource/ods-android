## Introduction

Orange is providing a full Design System to build Orange Mobile Application. The objective of the [Orange Design System](https://system.design.orange.com/0c1af118d/p/019ecc-android/) (ODS) is to propose a set of guidelines on how to apply the Orange Brand on mobile applications. The Orange design System also provides a series of components and modules that show in details how to use this in the Orange apps.

The Orange Design System has been implemented in a code library that provides:
- a Jetpack Compose code library
- a demo app that can be launched to show the components and modules
- this demo app also shows how to use the lib or style existing components

Using these resources will allow you to create Orange branded applications faster and will inherit all the work that was done to make sure that all presented codes are fully tested with regard to the brand and the accessibility compliance.

## Instructions

### 1. Depend on our library

Orange Design System for Android is available through [Maven Central Repository](https://mvnrepository.com/artifact/com.orange.ods.android). To use it:

1. Open the `build.gradle` file for your application.
2. Make sure that the `repositories` section includes Maven Central. For example:

   ```groovy
     allprojects {
       repositories {
         google()
         mavenCentral()
       }
     }
   ```

3. Add the library to the `dependencies` section:

   ```groovy
     dependencies {
       // ...
       implementation 'com.orange.ods.android:ods-lib:0.6.0'
       // ...
     }
   ```

4. (Optional) Add an `OdsThemeConfiguration` implementation library to the `dependencies` section:

You have the possibility to use the ODS library with another theme than the Orange theme.

   ```groovy
     dependencies {
       // ...
       implementation(project(":theme-innovation-cup"))
       // ...
     }
   ```

### 2. Compile your app with Android 11

Orange Design System library depends on Material Design library from Google. For this reason, you
will have to install Android Studio 4.0 or higher to build with Android 11, and update your
app's `compileSdkVersion` to `31`.

### 3. Ensure you are using `AppCompatActivity`

Using `AppCompatActivity` will ensure that all the components work correctly. If you are unable to
extend from `AppCompatActivity`, update your activities to use
`AppCompatDelegate`. This will enable the `AppCompat` versions of components to be inflated among
other important things.

### 4. Change your app theme to inherit from a Orange Design theme

Note that Orange theme supports both light and dark mode.

#### In XML app

Update your app theme to inherit from Orange theme, e.g.:
```xml
<style name="Theme.MyApp" parent="Theme.Orange">
    <!-- ... -->
</style>
```

This theme will use the default `Toolbar`. If you want to provide your own `Toolbar` please use:
```xml
<style name="Theme.MyApp" parent="Theme.Orange.NoActionBar">
    <!-- ... -->
</style>
```

#### In Jetpack Compose app

In the `Manifest.xml` file, add `Theme.Orange.NoActionBar` to your application:
```xml
    <application
        android:theme="@style/Theme.Orange.NoActionBar">
        <!-- ... -->
    </application>
```

Use the `OdsMaterialTheme` in your screens which is a Material theme extension for Jetpack Compose applications:
```kotlin
    OdsMaterialTheme {
        //...
    }
```
