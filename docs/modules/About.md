---
layout: detail
title: About
description: An about screen should be displayed in all Orange applications to display the application name, software version as well as all legal data protection, privacy, and terms of service compliance information.
---

The ODS About module displays the following mandatory menu items: *Privacy policy* and *Terms of service*.  
It also allows you to add optional predefined menu items: *App news*, *Legal information* and *Rate the app*.  
Moreover you can add your own menu items linked to a local file or an URL.

Share and feedback functionalities are optional but can be added and configured.

<br>**On this page**

* Table of contents
{:toc}

---

## Specifications references

- [Design System Manager - About](https://system.design.orange.com/0c1af118d/p/80ec10-about/b/31ce28)

## Accessibility

Please follow [accessibility criteria for development](https://a11y-guidelines.orange.com/en/mobile/android/development/).

The ODS About module is built to support accessibility criteria and is readable by most screen readers, such as TalkBack.

## Integration

![About light](images/about_light.png)

![About dark](images/about_dark.png)

### Jetpack Compose

Follow these steps in order to integrate an ODS About into your app:

1. Add the ODS About module to the `dependencies` section of your `build.gradle` file:

    ```groovy
    dependencies {
        // ...
        implementation 'com.orange.ods.android:ods-module-about:1.0.0'
        // ...
    }
    ```

2. Add ODS About graph to your app navigation graph and provide the app `NavController` instance as well as a lambda that returns a configuration for the module (see [Configuration chapter](#configuration)).

    ```kotlin
    NavHost(
        navController = navController,
        startDestination = StartRoute,
        modifier = Modifier.padding(innerPadding)
    ) {
        //...
        odsAboutGraph(navController) {
            OdsAboutConfiguration(
                appName = "App name",
                privacyPolicyMenuItemFile = OdsAboutFileMenuItem.File(R.raw.about_privacy_policy, OdsAboutFileMenuItem.File.Format.Html),
                termsOfServiceMenuItemFile = OdsAboutFileMenuItem.File(R.raw.about_terms_of_service, OdsAboutFileMenuItem.File.Format.Html),
            )
        }
        //...
    }
    ```

3. Use the `NavController.navigate()` method with `OdsAboutDestinations.AboutRoute` parameter when you need to display the previously configured ODS About.

    ```kotlin
    navController.navigate(OdsAboutDestinations.AboutRoute)
    ```

## Configuration

In order to configure the ODS About Module, you need to provide an `OdsAboutConfiguration`. The properties of this class are explained below.

### OdsAboutConfiguration API

| Property                                                       | Default&nbsp;value    | Description                                                                                                                                                                                                                                                     |
|----------------------------------------------------------------|-----------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| <b>`appName: String`</b>                                       |                       | The name of the application displayed on the main screen of the About module                                                                                                                                                                                    |
| <b>`privacyPolicyMenuItemFile: OdsAboutFileMenuItem.File`</b>  |                       | The privacy policy menu item file. Note that this menu item is mandatory and you MUST provide the corresponding file in the raw directory to display the privacy policy of your app.                                                                            |
| <b>`termsOfServiceMenuItemFile: OdsAboutFileMenuItem.File`</b> |                       | The terms of service menu item file. Note that this menu item is mandatory and you MUST provide the corresponding file in the raw directory to display the terms of service for your app.                                                                       |
| `appIllustrationRes: OdsBanner.Image?`                         | `R.drawable.il_about` | The illustration resource id. It should be a SVG or PNG resource file, placed in res/drawable directory. It allows to customize the displayed image on the main screen of the About module. If not provided, the default Orange illustration will be displayed. |
| `appVersion: String?`                                          | `null`                | The application version displayed on the main screen of the About module. If null, no version will be displayed. Note that you can use the provided `OdsAboutVersionHelper` to display a version using your package information.                                |
| `appDescription: String?`                                      | `null`                | The application description displayed on the main screen of the About module. If null, no description will be displayed.                                                                                                                                        |
| `shareData: OdsAboutShareData?`                                | `null`                | The data to be used to share the app on share button click. If null, no share button will be displayed.                                                                                                                                                         |
| `onFeedbackButtonClick: (() -> Unit)?`                         | `null `               | The action to be launched on feedback button click. If null, no feedback button will be displayed.                                                                                                                                                              |
| `topAppBarActions: List<OdsTopAppBar.ActionButton>`            | `emptyList()`         | The optional actions displayed at the end of the About module TopAppBar.                                                                                                                                                                                        |
| `topAppBarOverflowMenuActions: List<OdsDropdownMenu.Item>`     | `emptyList()`         | The optional actions displayed in the overflow menu of the About module TopAppBar. If the list is empty, the overflow menu icon will not be displayed.                                                                                                          |
| `appNewsMenuItemFileRes: Int?`                                 | `null`                | App news menu item JSON file resource. Provide it to display an App news menu item linked to this file. Be careful to respect the [App news JSON format](#app-news).                                                                                            |
| `legalInformationMenuItemFile: OdsAboutFileMenuItem.File?`     | `null`                | Legal information menu item file. Provide it to display a Legal information menu item linked to this file.                                                                                                                                                      |
| `rateTheAppUrl: String?`                                       | `null`                | Rate the app URL. Provide it to display a Rate the app menu item linked to this URL.                                                                                                                                                                            |
| `customMenuItems: List<OdsAboutMenuItem>`                      | `emptyList()`         | The custom menu items to be displayed on the about main screen. Note that mandatory items will be added to the provided list: Privacy policy (position = 100), Term of services (position = 101), Accessibility (position = 102).                               |
| `onScreenChange: ((title: String) -> Unit)?`                   | `null`                | Callback invoked on about module screen change. It can help you managing top app bar title update if necessary.                                                                                                                                                 |

### App news

To add the *App news* functionality in your about screen, you need to provide a JSON file which MUST respect the following schema:

```json
{
  "$schema": "http://json-schema.org/draft-04/schema#",
  "title": "Release set",
  "type": "array",
  "items": {
    "title": "Release",
    "type": "object",
    "properties": {
      "version": {
        "description": "The release version",
        "type": "string"
      },
      "date": {
        "description": "The date of the release",
        "type": "string"
      },
      "news": {
        "description": "The release description with the notable elements",
        "type": "string"
      }
    },
    "required": [
      "version",
      "date",
      "news"
    ]
  }
}
```

Here is an example of a valid *App news* JSON file:

```json
[
  {
    "version": "0.17.0",
    "date": "2023-11-08",
    "news": "- Several API changes: Bottom navigation, choice chips, sliders, list items and snackbars\n- Include ODS composables parameters description in the documentation"
  },
  {
    "version": "0.16.0",
    "date": "2023-10-11",
    "news": "- Several API changes: Cards ans sliders"
  }
]
```

