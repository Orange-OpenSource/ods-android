---
layout: detail
title: Button
description: Buttons allow users to take actions, and make choices, with a single tap.
---

**Contents**

* [Material Design](#material-design)
* [Accessibility](#accessibility)
* [Text button](#text-button)
  * [Adding a text button in XML](#adding-a-text-button-in-xml)
  * [Adding a text button with icon in XML](#adding-a-text-button-with-icon-in-xml)
  * [Adding a text button in Jetpack Compose](#adding-a-text-button-in-jetpack-compose)
* [Outlined button](#outlined-button)
  * [Adding an outlined button in XML](#adding-an-outlined-button-in-xml)
  * [Adding an outlined button with icon in XML](#adding-an-outlined-button-with-icon-in-xml)
  * [Adding an outlined button in Jetpack Compose](#adding-an-outlined-button-in-jetpack-compose)
* [Contained button](#contained-button)
  * [Adding a contained button in XML](#adding-a-contained-button-in-xml)
  * [Adding a contained button with icon in XML](#adding-a-contained-button-with-icon-in-xml)
  * [Adding a contained button in Jetpack Compose](#adding-a-contained-button-in-jetpack-compose)
* [Toggle button](#toggle-button)
  * [Adding a toggle button in XML](#adding-a-toggle-button-in-xml)
  * [Adding a toggle button icon-only in XML](#adding-a-toggle-button-icon-only-in-xml)
  * [Adding a toggle button in Jetpack Compose](#adding-a-toggle-button-in-jetpack-compose)


Before you can use Orange themed buttons, you need to add a dependency to the Orange Design System
for Android library. For more information, go to the
[Getting started](../home_content.md) page.

## Material Design

Orange Buttons are based on Material Design from Google and apply Orange theming.
**Note:** Here is the full documentation
of [Material Design Button](https://material.io/components/buttons/)

## Accessibility

Buttons support content labeling for accessibility and are readable by most screen readers, such as
TalkBack. Text rendered in buttons is automatically provided to accessibility services. Additional
content labels are usually unnecessary.

## Text button

Text buttons are typically used for less-pronounced actions, including those located in dialogs and
cards. In cards, text buttons help maintain an emphasis on card content.

  ![TextButton](images/button_text_light.png) ![TextButton dark](images/button_text_dark.png)

### Adding a text button in XML

To create a Text Button using Orange theme you will need to apply
style `@style/Widget.Orange.Button.Text` on your `Button` layout

In the layout:

```xml
<Button android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Text button"
        style="@style/Widget.Orange.Button.Text" />
```

### Adding a text button with icon in XML

To create a Text Button having an icon using Orange theme you will need to apply
style `@style/Widget.Orange.Button.Text.Icon` on your `Button` layout

In the layout:

```xml
<Button android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Text button with icon"
        app:icon="@drawable/ic_add_24dp"
        style="@style/Widget.Orange.Button.Text.Icon" />
```

### Adding a text button in Jetpack Compose

Use the `OdsButtonText` composable:

```kotlin
        OdsButtonText(text = "Text button", onClick = {}, enabled = true)
```

## Outlined button

Outlined buttons are medium-emphasis buttons. They contain actions that are important, but aren’t
the primary action in an app.

  ![ButtonOutlined](images/button_outlined_light.png) ![ButtonOutlined dark](images/button_outlined_dark.png)

### Adding an outlined button in XML

To create an Outlined Button using Orange theme you will need to apply
style `@style/Widget.Orange.Button.Outlined` on your `Button` layout

In the layout:

```xml
<Button android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Outlined button"
        style="@style/Widget.Orange.Button.Outlined" />
```

### Adding an outlined button with icon in XML

To create an Outlined Button having an icon using Orange theme you will need to apply
style `@style/Widget.Orange.Button.Outlined.Icon` on your `Button` layout

In the layout:

```xml
<Button android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Outlined button with icon"
        app:icon="@drawable/ic_add_24dp"
        style="@style/Widget.Orange.Button.Outlined.Icon" />
```

### Adding an outlined button in Jetpack Compose

Use the `OdsButtonOutlined` composable:

```kotlin
        OdsButtonOutlined(text = "Text button", onClick = {}, iconRes = R.drawable.ic_search, enabled = true)
```

## Contained button

Contained buttons are high-emphasis, distinguished by their use of elevation and fill. They contain
actions that are primary to your app.

  ![ContainedButton](images/button_contained_light.png) ![ContainedButton dark](images/button_contained_dark.png)

### Adding a contained button in XML

_**Note** The contained button is the default style if the style is not set._

To create a Contained Button using Orange theme you will need to apply
style `@style/Widget.Orange.Button.Contained` on your `Button` layout

In the layout:

```xml
<Button android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Contained button"
        style="@style/Widget.Orange.Button.Contained" />
```

or

```xml
<Button android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Contained button" />
```

### Adding a contained button with icon in XML

To create a Contained Button having an icon using Orange theme you will need to apply
style `@style/Widget.Orange.Button.Contained.Icon` on your `Button` layout

In the layout:

```xml
<Button android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Contained button with icon"
        app:icon="@drawable/ic_add_24dp"
        style="@style/Widget.Orange.Button.Contained.Icon" />
```

### Adding a contained button in Jetpack Compose

Use the `OdsButton` composable:

```kotlin
        OdsButton(text = "Text button", onClick = {}, iconRes = R.drawable.ic_search, enabled = true)
```

## Toggle button

To emphasize groups of related toggle buttons, a group should share a common container.

Orange Toggle Buttons refers to Material Design Toggle Buttons.

  ![ButtonToggle](images/button_toggle_light.png) ![ButtonToggle dark](images/button_toggle_dark.png)

### Adding a toggle button in XML

API and source code:

* `MaterialButtonToggleGroup`
    * [Class description](https://developer.android.com/reference/com/google/android/material/button/MaterialButtonToggleGroup)
    * [Class source](https://github.com/material-components/material-components-android/tree/master/lib/java/com/google/android/material/button/MaterialButtonToggleGroup.java)
* `MaterialButton`
    * [Class description](https://developer.android.com/reference/com/google/android/material/button/MaterialButton)
    * [Class source](https://github.com/material-components/material-components-android/tree/master/lib/java/com/google/android/material/button/MaterialButton.java)

In the layout:

```xml
<com.google.android.material.button.MaterialButtonToggleGroup
        android:layout_width="wrap_content"
        android:layout_height="wrap_content">
    <Button android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Button 1"
            style="@style/Widget.Orange.Button.Outlined" />
    <Button android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Button 2"
            style="@style/Widget.Orange.Button.Outlined" />
    <Button android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:text="Button 3"
            style="@style/Widget.Orange.Button.Outlined" />
</com.google.android.material.button.MaterialButtonToggleGroup>
```

#### Adding a toggle button icon-only in XML

To create an icon-only toggle button using Orange theme you will need to apply
style `@style/Widget.Orange.Button.Outlined.IconOnly` on your `Button` layout

In the layout:

```xml
<com.google.android.material.button.MaterialButtonToggleGroup
        android:layout_width="wrap_content"
        android:layout_height="wrap_content">
  <Button android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:text="Button 1"
          style="@style/Widget.Orange.Button.Outlined.IconOnly" />
  <Button android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:text="Button 2"
          style="@style/Widget.Orange.Button.Outlined.IconOnly" />
  <Button android:layout_width="wrap_content"
          android:layout_height="wrap_content"
          android:text="Button 3"
          style="@style/Widget.Orange.Button.Outlined.IconOnly" />
</com.google.android.material.button.MaterialButtonToggleGroup>
```

#### Adding a toggle button in Jetpack Compose

Use the `OdsButtonToggle` composable:

```kotlin
OdsButtonToggle(checked = false, onCheckedChange = { }, iconRes = iconRes, contentDescription = "action")
```

