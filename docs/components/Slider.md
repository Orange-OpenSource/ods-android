---
layout: detail
title: Slider
description: Sliders allow users to make selections from a range of values.
---

**Page Summary**

* [Specifications references](#specifications-references)
* [Accessibility](#accessibility)
* [Variants](#variants)
  * [Continuous slider](#continuous-slider)
  * [Continuous lockups slider](#continuous-lockups-slider)
  * [Discrete slider](#discrete-slider)
  * [Discrete lockup slider](#discrete-lockup-slider)


## Specifications references

- [Design System Manager - Sliders](https://system.design.orange.com/0c1af118d/p/8077fc-sliders/b/673558)
- [Material Design - Sliders](https://material.io/components/sliders/)

## Accessibility

Please follow [accessibility criteria for development](https://a11y-guidelines.orange.com/en/mobile/android/development/)

Sliders support setting content descriptors for use with screen readers. While
optional, we strongly encourage their use.

That can be done in XML via the `android:contentDescription` attribute or
programmatically like so:

```kotlin
slider.contentDescription = contentDescription
```

Additionally, if using a `TextView` to display the value of the slider, you
should set `android:labelFor` on it, so that screen readers announce that
`TextView` refers to the slider.

## Variants

## Continuous slider

Continuous sliders allow users to make meaningful selections that don’t require
a specific value.

![Continuous slider](images/slider_continuous_light.png) ![Continuous slider dark](images/slider_continuous_dark.png)

With icons:

![Continuous slider with icons](images/slider_continuous_with_icon_light.png) ![Continuous slider with icons dark](images/slider_continuous_with_icon_dark.png)

> **Jetpack Compose implementation**

In your composable screen you can use:

```kotlin
OdsSlider(
    value = 20f,
    valueRange = 0f..100f,
    onValueChange = { }
)
```

You can add icons to the continuous slider like this:

```kotlin
OdsSlider(
    value = 20f,
    valueRange = 0f..100f,
    onValueChange = { },
    leftIconRes = R.drawable.ic_heart,
    rightIconRes = R.drawable.ic_heart
)
```

> **XML implementation**

API and source code:

*   `Slider`: [Class definition](https://developer.android.com/reference/com/google/android/material/slider/Slider), [Class source](https://github.com/material-components/material-components-android/tree/master/lib/java/com/google/android/material/slider/Slider.java)

Note: By default, the slider will show a value label above the thumb when it's
selected. You can change how it's drawn via the `app:labelBehavior` attribute or
`setLabelBehavior` method.

The modes of `app:labelBehavior` are:

*   `floating` (default) - draws the label floating above the bounds of this
    view
*   `withinBounds` - draws the label floating within the bounds of this view
*   `gone` - prevents the label from being drawn

**Adding a continuous slider in the layout**

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

## Continuous lockups slider

![Continuous lockups slider](images/slider_continuous_lockups_light.png) ![Continuous lockups slider dark](images/slider_continuous_lockups_light.png)

> **Jetpack Compose implementation**

In your composable screen you can use:

```kotlin
        OdsSliderLockups(
            value = 20f,
            valueRange = 0f..100f,
            onValueChange = { }
        )
```

### Discrete slider

Discrete sliders display a numeric value label upon pressing the thumb, which
allows a user to input an exact value.

![Discrete slider](images/slider_discrete_light.png) ![Discrete slider dark](images/slider_discrete_dark.png)

With icons:

![Discrete slider with icon](images/slider_discrete_with_icon_light.png) ![Discrete slider with icon dark](images/slider_discrete_with_icon_dark.png)

> **Jetpack Compose implementation**

In your composable screen you can use:

```kotlin
OdsSlider(
    value = 20f,
    valueRange = 0f..100f,
    steps = 10,
    onValueChange = { }
)
```

You can add icons to the discrete slider like this:

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

> **XML implementation**

API and source code:

*   `Slider`: [Class definition](https://developer.android.com/reference/com/google/android/material/slider/Slider), [Class source](https://github.com/material-components/material-components-android/tree/master/lib/java/com/google/android/material/slider/Slider.java)

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

### Discrete lockup slider

![Discrete lockups slider](images/slider_discrete_lockups_light.png) ![Discrete lockups slider dark](images/slider_discrete_lockups_dark.png)

> **Jetpack Compose implementation**

In your composable screen you can use:

```kotlin
        OdsSliderLockups(
            value = 20f,
            valueRange = 0f..100f,
            onValueChange = { }
        )
```
