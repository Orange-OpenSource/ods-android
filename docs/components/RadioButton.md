---
layout: detail
title: Radio Button
description: Radio Button description
---

# Selection controls: radio buttons

Selection controls allow the user to select options.

Use radio buttons to:

*   Select a single option from a list
*   Expose all available options
*   If available options can be collapsed, consider using a dropdown menu
    instead, as it uses less space.

**Contents**

*   [Using radio buttons](#using-radio-buttons)

## Using radio buttons

Before you can use Orange themed radio buttons, you need to add a dependency to the Orange Design
System for Android library. For more information, go to the
[Getting started](../getting-started.md) page.

### Material Design

Orange Radio Buttons are based on Material Design from Google and apply Orange theming.
**Note:** Here is the full documentation
of [Material Design Radio Button](https://material.io/components/radio-buttons/)

### Accessibility

Radio buttons support content labeling for accessibility and are readable by
most screen readers, such as TalkBack. Text rendered in radio buttons is
automatically provided to accessibility services. Additional content labels are
usually unnecessary.

### Adding a radio button

![RadioButton](images/radio_button_light.png) ![RadioButton dark](images/radio_button_dark.png)

#### In XML

To create a Radio Button you just have to add a `RadioButton` in your layout. Orange theme will be
automatically applied

In the layout:

```xml
<RadioButton
    android:id="@+id/radio_button"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:text="@string/label"/>
```

#### In JetPack Compose

In your composable screen you can use:

```kotlin
OdsRadioButton(
    selected = true,
    onClick = { },
    enabled = true
)
```

### Grouping radio buttons

Changes in the states of one radio button can affect other buttons in the group.
Specifically, selecting a `RadioButton` in a `RadioGroup` will de-select all
other buttons in that group.

Example showing a radio button group with five radio buttons.

In the layout:

```xml
<RadioGroup
    android:id="@+id/radioGroup"
    android:checkedButton="@+id/radio_button_1"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">
    <RadioButton
        android:id="@+id/radio_button_1"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:text="@string/label_1"/>
    <RadioButton
        android:id="@+id/radio_button_2"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:text="@string/label_2"/>
    <RadioButton
        android:id="@+id/radio_button_3"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:text="@string/label_3"/>
    <RadioButton
        android:id="@+id/radio_button_4"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:text="@string/label_4"/>
    <RadioButton
        android:id="@+id/radio_button_5"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:enabled="false"
        android:text="@string/label_5"/>
</RadioGroup>
```