---
layout: detail
title: Banners
description: Banners displays an important message which requires an action to be dismissed.
---

A banner displays an important, succinct message, and provides actions for users to address (or dismiss the banner).
It requires a user action to be dismissed.

Banners should be displayed at the top of the screen, below a top app bar. They’re persistent and nonmodal, allowing the user to either ignore them or interact with them at any time.
Only one banner should be shown at a time

<br>**On this page**

* [Specifications references](#specifications-references)
* [Accessibility](#accessibility)
* [Implementation](#implementation)
    * [Jetpack Compose](#jetpack-compose)
        * [OdsBanner API](#odsbanner-api)
    * [XML](#xml)

---

## Specifications references

- [Design System Manager - Banners](https://system.design.orange.com/0c1af118d/p/19a040-banners/b/497b77)
- [Material Design - Banners](https://m2.material.io/components/banners)

## Accessibility

Please follow [accessibility criteria for development](https://a11y-guidelines.orange.com/en/mobile/android/development/).

`OdsBanner` is built to support accessibility criteria and is readable by most screen readers, such as TalkBack. The use of an `OdsBanner.Image` force the developer to associate a content description to the banner image.

## Implementation

![Banner light](images/banner_light.png)

![Banner dark](images/banner_dark.png)

### Jetpack Compose

You can use the `OdsBanner` composable like this:

```kotlin
OdsBanner(
    message = "Message displayed into the banner.",
    firstButton = OdsBanner.Button("Dismiss") { doSomething() },
    secondButton = OdsBanner.Button("Detail") { doSomething() },
    image = OdsBanner.Image(painterResource(id = R.drawable.placeholder), "")
)
```

#### OdsBanner API

{:.table}

| Parameter                         | Default&nbsp;value | Description                                                        |
|-----------------------------------|--------------------|--------------------------------------------------------------------|
| <b>`message: String`</b>          |                    | Text displayed into the banner                                     |
| `modifier: Modifier`              | `Modifier`         | `Modifier` applied to the banner                                   |
| `image: OdsBanner.Image?`         | `null`             | Image displayed in the banner in a circle shape                    |
| `firstButton: OdsBanner.Button?`  | `null`             | Primary button displayed in the banner                             |
| `secondButton: OdsBanner.Button?` | `null`             | Secondary button displayed into the banner next to the primary one |

### XML

In your layout, use the `OdsBanner` view as shown below:

```xml

<com.orange.ods.xml.component.banner.OdsBanner
    android:id="@+id/ods_banner"
    android:layout_height="wrap_content"
    android:layout_width="match_parent"
    app:message="Message displayed into the banner."
    app:firstButtonText="Dismiss"
    app:secondButtonText="Detail"
    app:image="@drawable/placeholder"
    app:imageContentDescription="Banner image" />
```