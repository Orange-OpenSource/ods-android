---
layout: detail
title: Buttons
description: Buttons allow users to take actions, and make choices, with a single tap.
---

---

**Page Summary**

* [Specifications references](#specifications-references)
* [Accessibility](#accessibility)
* [Variants](#variants)
    * [Text button](#text-button)
    * [Outlined button](#outlined-button)
    * [Contained button](#contained-button)
    * [Toggle button](#toggle-button)
    * [Icon button](#icon-button)
* [Component specific tokens](#component-specific-tokens)

---

## Specifications references

- [Design System Manager - Buttons](https://system.design.orange.com/0c1af118d/p/06a393-buttons/b/530521)
- [Material Design - Buttons](https://material.io/components/buttons/)
- Technical documentation soon available

## Accessibility

Please follow [accessibility criteria for development](https://a11y-guidelines.orange.com/en/mobile/android/development/)

Buttons support content labeling for accessibility and are readable by most screen readers, such as
TalkBack. Text rendered in buttons is automatically provided to accessibility services. Additional
content labels are usually unnecessary.

## Variants

### Text button

Text buttons are typically used for less-pronounced actions, including those located in dialogs and
cards. In cards, text buttons help maintain an emphasis on card content.

  ![TextButton](images/button_text_light.png) ![TextButton dark](images/button_text_dark.png)

> **Jetpack Compose implementation**

Use the `OdsTextButton` composable:

```kotlin
OdsTextButton(
    text = "Text button", 
    onClick = {}, 
    enabled = true, 
    icon = painterResource(R.drawable.ic_coffee), // Optional, line can be removed if you don't need any icon
    style = OdsTextButtonStyle.Primary
)
```

> **XML implementation**

To create a Text Button using Orange theme you will need to apply
style `@style/Widget.Orange.Button.Text` on your `Button` layout

In the layout:

```xml
<Button android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Text button"
        style="@style/Widget.Orange.Button.Text" />
```

To create a **Text Button having an icon** using Orange theme you will need to apply
style `@style/Widget.Orange.Button.Text.Icon` on your `Button` layout

```xml
<Button android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Text button with icon"
        app:icon="@drawable/ic_add_24dp"
        style="@style/Widget.Orange.Button.Text.Icon" />
```

### Outlined button

Outlined buttons are medium-emphasis buttons. They contain actions that are important, but aren’t
the primary action in an app.

  ![ButtonOutlined](images/button_outlined_light.png) ![ButtonOutlined dark](images/button_outlined_dark.png)

> **Jetpack Compose implementation**

Use the `OdsOutlinedButton` composable:

```kotlin
OdsOutlinedButton(
    text = "Outlined button", 
    onClick = {}, 
    enabled = true,
    icon = painterResource(R.drawable.ic_coffee) // Optional, line can be removed if you don't need any icon
)
```

> **XML implementation**

To create an Outlined Button using Orange theme you will need to apply
style `@style/Widget.Orange.Button.Outlined` on your `Button` layout.

In the layout:

```xml
<Button android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Outlined button"
        style="@style/Widget.Orange.Button.Outlined" />
```

To create an **Outlined Button having an icon** using Orange theme you will need to apply
style `@style/Widget.Orange.Button.Outlined.Icon` on your `Button` layout.

```xml
<Button android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Outlined button with icon"
        app:icon="@drawable/ic_add_24dp"
        style="@style/Widget.Orange.Button.Outlined.Icon" />
```

### Contained button

Contained buttons are high-emphasis, distinguished by their use of elevation and fill. They contain
actions that are primary to your app.

  ![ContainedButton](images/button_contained_light.png) ![ContainedButton dark](images/button_contained_dark.png)

Functional positive:

  ![ContainedButton positive light](images/button_contained_positive_light.png) ![ContainedButton positive dark](images/button_contained_positive_dark.png)

Functional negative:

  ![ContainedButton negative light](images/button_contained_negative_light.png) ![ContainedButton negative dark](images/button_contained_negative_dark.png)

> **Jetpack Compose implementation**

Use the `OdsButton` composable:

```kotlin
OdsButton(
    text = "Contained button", 
    onClick = {}, 
    enabled = true,
    icon = painterResource(R.drawable.ic_coffee) // Optional, line can be removed if you don't need any icon
)
```

To display a primary button or a functional green/red button, you need to pass an `OdsButtonStyle` through the `style` parameter:

```kotlin
OdsButton(
    text = "Positive button", 
    onClick = {}, 
    enabled = true,
    icon = painterResource(R.drawable.ic_coffee), // Optional, line can be removed if you don't need any icon
    style = OdsButtonStyle.FunctionalPositive
)
```


> **XML implementation**

_**Note** In XML, the contained button is the default style if the style is not set._

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

To create a **Contained Button having an icon** using Orange theme you will need to apply
style `@style/Widget.Orange.Button.Contained.Icon` on your `Button` layout

```xml
<Button android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Contained button with icon"
        app:icon="@drawable/ic_add_24dp"
        style="@style/Widget.Orange.Button.Contained.Icon" />
```


### Toggle button

To emphasize groups of related toggle buttons, a group should share a common container.

  ![ButtonToggle](images/button_toggle_light.png) ![ButtonToggle dark](images/button_toggle_dark.png)

> **Jetpack Compose implementation**

Use the `OdsIconToggleButton` composable:

```kotlin
OdsIconToggleButton(
    checked = false, 
    onCheckedChange = { }, 
    icon = painterResource(R.drawable.ic_coffee),
    contentDescription = "action"
)
```

> **XML implementation**

API and source code:

* `MaterialButtonToggleGroup`: [Class description](https://developer.android.com/reference/com/google/android/material/button/MaterialButtonToggleGroup), [Class source](https://github.com/material-components/material-components-android/tree/master/lib/java/com/google/android/material/button/MaterialButtonToggleGroup.java)
* `MaterialButton`: [Class description](https://developer.android.com/reference/com/google/android/material/button/MaterialButton), [Class source](https://github.com/material-components/material-components-android/tree/master/lib/java/com/google/android/material/button/MaterialButton.java)

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

To create an **icon-only toggle button** using Orange theme you will need to apply
style `@style/Widget.Orange.Button.Outlined.IconOnly` on your `Button` layout.

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

### Icon button

An icon button is a clickable icon, used to represent actions. This component is typically used inside an App Bar for the navigation icon / actions.

  ![OdsIconButton](images/button_icon_light.png) ![OdsIconButton dark](images/button_icon_dark.png)

> **Jetpack Compose implementation**

Use the `OdsIconButton` composable:

```kotlin
OdsIconButton(
    onClick = { },
    painter = painterResource(id = R.drawable.ic_ui_light_mode),
    contentDescription = stringResource(id = R.string.theme_changer_icon_content_description_light)
)
```


## Component specific tokens

_Soon available_
