---
layout: detail
title: Button
description: Button description
---

# Buttons

Buttons allow users to take actions, and make choices, with a single tap.

**Contents**

* [Using buttons](#using-buttons)
* [Text button](#text-button)
* [Outlined button](#outlined-button)
* [Contained button](#contained-button)
* [Toggle button](#toggle-button)

## Using buttons

Before you can use Orange themed buttons, you need to add a dependency to the Orange Design System
for Android library. For more information, go to the
[Getting started](../getting-started.md) page.

### Material Design

Orange Buttons are based on Material Design from Google and apply Orange theming.
**Note:** Here is the full documentation
of [Material Design Button](https://material.io/components/buttons/)

### Accessibility

Buttons support content labeling for accessibility and are readable by most screen readers, such as
TalkBack. Text rendered in buttons is automatically provided to accessibility services. Additional
content labels are usually unnecessary.

### Types

There are four types of buttons:

- [Text button](#text-button)
- [Outlined button](#outlined-button)
- [Contained button](#contained-button)
- [Toggle button](#toggle-button)

## Text button

Text buttons are typically used for less-pronounced actions, including those located in dialogs and
cards. In cards, text buttons help maintain an emphasis on card content.

### Adding a text button

To create a Text Button using Orange theme you will need to apply
style `@style/Widget.Orange.Button.Text` on your `Button` layout

In the layout:

```xml
<Button android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Text button"
        style="@style/Widget.Orange.Button.Text" />
```

### Adding a text button with icon

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

## Outlined button

Outlined buttons are medium-emphasis buttons. They contain actions that are important, but aren’t
the primary action in an app.

### Adding an outlined button

To create an Outlined Button using Orange theme you will need to apply
style `@style/Widget.Orange.Button.Outlined` on your `Button` layout

In the layout:

```xml
<Button android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Outlined button"
        style="@style/Widget.Orange.Button.Outlined" />
```

### Adding an outlined button with icon

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

## Contained button

Contained buttons are high-emphasis, distinguished by their use of elevation and fill. They contain
actions that are primary to your app.

_**Note** The contained button is the default style if the style is not set._

### Adding a contained button

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

### Adding a contained button with icon

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

### Toggle button

To emphasize groups of related toggle buttons, a group should share a common container.

Orange Toggle Buttons refers to Material Design Toggle Buttons.

#### Adding a toggle button

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

#### Adding an icon-only toggle button

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