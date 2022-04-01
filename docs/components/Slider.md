---
layout: detail
title: Slider
description: Slider description
---

# Slider

Sliders allow users to make selections from a range of values.

**Contents**

* [Using sliders](#using-sliders)
* [Continuous slider](#continuous-slider)
* [Discrete slider](#discrete-slider)

## Using sliders

Before you can use Orange themed sliders, you need to add a dependency to the Orange Design System
for Android library. For more information, go to the
[Getting started](../getting-started.md) page.

### Material Design

Orange Sliders are based on Material Design from Google and apply Orange theming.
**Note:** Here is the full documentation
of [Material Design Sliders](https://material.io/components/sliders/)

### Accessibility

Sliders support setting content descriptors for use with screen readers. While
optional, we strongly encourage their use.

That can be done in XML via the `android:contentDescription` attribute or
programmatically like so:

```kt
slider.contentDescription = contentDescription
```

Additionaly, if using a `TextView` to display the value of the slider, you
should set `android:labelFor` on it, so that screen readers announce that
`TextView` refers to the slider.

### Adding/removing the value label

By default, the slider will show a value label above the thumb when it's
selected. You can change how it's drawn via the `app:labelBehavior` attribute or
`setLabelBehavior` method.

The modes of `app:labelBehavior` are:

*   `floating` (default) - draws the label floating above the bounds of this
    view
*   `withinBounds` - draws the label floating within the bounds of this view
*   `gone` - prevents the label from being drawn


### Types

There are two types of sliders: 
- [Continuous slider](#continuous-slider)
- [Discrete slider](#discrete-slider)

## Continuous slider

Continuous sliders allow users to make meaningful selections that don’t require
a specific value.

API and source code:

*   `Slider`
    *   [Class definition](https://developer.android.com/reference/com/google/android/material/slider/Slider)
    *   [Class source](https://github.com/material-components/material-components-android/tree/master/lib/java/com/google/android/material/slider/Slider.java)

### Adding in XML

In the layout:

```xml
<com.google.android.material.slider.Slider
    android:id="@+id/slider"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:contentDescription="@string/slider_desc"
    android:value="20.0"
    android:valueFrom="0.0"
    android:valueTo="100.0" />
```

### Adding in Jetpack Compose

![RadioButton](images/slider_continuous_light.png) ![RadioButton dark](images/slider_continuous_dark.png)

In your composable screen you can use:

```kotlin
OdsSlider(
    value = 20f,
    valueRange = 0f..100f,
    onValueChange = { }
)
```

#### Continuous with icons

![RadioButton](images/slider_continuous_with_icon_light.png) ![RadioButton dark](images/slider_continuous_with_icon_dark.png)

In your composable screen you can use:

```kotlin
OdsSlider(
    value = 20f,
    valueRange = 0f..100f,
    onValueChange = { },
    leftIconRes = R.drawable.ic_heart,
    rightIconRes = R.drawable.ic_heart
)
```

## Discrete slider

Discrete sliders display a numeric value label upon pressing the thumb, which
allows a user to input an exact value.

API and source code:

*   `Slider`
    *   [Class definition](https://developer.android.com/reference/com/google/android/material/slider/Slider)
    *   [Class source](https://github.com/material-components/material-components-android/tree/master/lib/java/com/google/android/material/slider/Slider.java)
    
### Adding in XML

In the layout:

```xml
<com.google.android.material.slider.Slider
    android:id="@+id/slider"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:contentDescription="@string/slider_desc"
    android:value="20.0"
    android:valueFrom="0.0"
    android:valueTo="100.0"    
    android:stepSize="5.0" />
```

### Adding in Jetpack Compose

![RadioButton](images/slider_discrete_light.png) ![RadioButton dark](images/slider_discrete_dark.png)

In your composable screen you can use:

```kotlin
OdsSlider(
    value = 20f,
    valueRange = 0f..100f,
    steps = 10,
    onValueChange = { }
)
```

#### Discrete slider with icons

![RadioButton](images/slider_discrete_with_icon_light.png) ![RadioButton dark](images/slider_discrete_with_icon_dark.png)

In your composable screen you can use:

```kotlin
OdsSlider(
    value = 20f,
    valueRange = 0f..100f,
    steps = 10,
    onValueChange = { },
    leftIconRes = R.drawable.ic_heart,
    rightIconRes = R.drawable.ic_heart
)
```