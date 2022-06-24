---
layout: detail
title: Progress indicator
description: Progress indicators express an unspecified wait time or display the length of a process.
---

**Page Summary**

* [Specifications references](#specifications-references)
* [Accessibility](#accessibility)
* [Variants](#variants)
    * [Progress bar](#progress-bar)
    * [Activity indicator](#activity-indicator)


## Specifications references

- [Design System Manager - Progress indicators](https://system.design.orange.com/0c1af118d/p/92aec5-progress-indicators------/b/33faf7)
- [Material Design - Progress indicators](https://material.io/components/progress-indicators/)

## Accessibility

Please follow [accessibility criteria for development](https://a11y-guidelines.orange.com/en/mobile/android/development/)

Progress indicators inherit accessibility support from the `ProgressBar` class in the framework.
Please consider setting the content descriptor for use with screen readers.

That can be done in XML via the `android:contentDescription` attribute or programmatically like so:

```kotlin
progressIndicator.contentDescription = contentDescription
```

## Variants

### Progress bar

Progress bars, also called linear progress indicators, display progress by animating an indicator along the length of a fixed,
visible track. The behavior of the indicator is dependent on whether the progress of a process is
known.

Linear progress indicators support both determinate and indeterminate operations.

* Determinate operations display the indicator increasing in width from 0 to 100% of the track, in
  sync with the process’s progress.
* Indeterminate operations display the indicator continually growing and shrinking along the track
  until the process is complete.

- **Jetpack Compose implementation**

You can use the native composable `LinearProgressIndicator` like this:

- For a **determinate** linear progress indicator
```kotlin
LinearProgressIndicator(progress = 0.2f)
```

- For an **indeterminate** linear progress indicator
```kotlin
LinearProgressIndicator()
```

- **XML implementation**

To create a linear progress indicator you will need to
add `com.google.android.material.progressindicator.LinearProgressIndicator` in your layout

API and source code:

*   `LinearProgressIndicator`: [Class description](https://developer.android.com/reference/com/google/android/material/progressindicator/LinearProgressIndicator), [Class source](https://github.com/material-components/material-components-android/tree/master/lib/java/com/google/android/material/progressindicator/LinearProgressIndicator.java)

The following example shows a **determinate** linear progress indicator.

In the layout:

```xml

<com.google.android.material.progressindicator.LinearProgressIndicator
    android:layout_width="match_parent" android:layout_height="wrap_content" />
```

The following example shows an **indeterminate** linear progress indicator.

In the layout:

```xml

<com.google.android.material.progressindicator.LinearProgressIndicator
    android:layout_width="match_parent" android:layout_height="wrap_content"
    android:indeterminate="true" />
```

### Activity indicator

Activity indicators, also called circular progress indicators, display progress by animating an indicator along an
invisible circular track in a clockwise direction. They can be applied directly
to a surface, such as a button or card.

Circular progress indicators support both determinate and indeterminate
processes.

*   Determinate circular indicators fill the invisible, circular track with
    color, as the indicator moves from 0 to 360 degrees.
*   Indeterminate circular indicators grow and shrink in size while moving along
    the invisible track.

- **Jetpack Compose implementation**

You can use the native `CircularProgressIndicator` like this:

- For a **determinate** circular progress indicator

```kotlin
CircularProgressIndicator(progress = 0.2f)
```

- For an **indeterminate** circular progress indicator

```kotlin
CircularProgressIndicator()
```

- **XML implementation**

To create a circular progress indicator you will need to
add `com.google.android.material.progressindicator.CircularProgressIndicator` in your layout

API and source code:

*   `CircularProgressIndicator`: [Class description](https://developer.android.com/reference/com/google/android/material/progressindicator/CircularProgressIndicator), [Class source](https://github.com/material-components/material-components-android/tree/master/lib/java/com/google/android/material/progressindicator/CircularProgressIndicator.java)

The following example shows a **determinate** circular progress indicator.

In the layout:

```xml
<com.google.android.material.progressindicator.CircularProgressIndicator
    android:layout_width="wrap_content"
    android:layout_height="wrap_content" />
```

The following example shows an **indeterminate** circular progress indicator.

In the layout:

```xml
<com.google.android.material.progressindicator.CircularProgressIndicator
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:indeterminate="true" />
```
