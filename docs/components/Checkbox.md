---
layout: detail
title: Checkbox
description: Checkbox selection control allows the user to select options.
---

Use checkboxes to:
* Select one or more options from a list
* Present a list containing sub-selections
* Turn an item on or off in a desktop environment

**Contents**

* [Using checkboxes](#using-checkboxes)
  *   [Material Design](#material-design)
  *   [Accessibility](#accessibility)
  *   [Adding a checkbox](#adding-a-checkbox)
      *   [In XML](#in-xml)
      *   [In Jetpack Compose](#in-jetpack-compose)

## Using checkboxes

Before you can use Orange themed checkboxes, you need to add a dependency to the Orange Design
System for Android library. For more information, go to the
[Getting started](../home_content.md) page.

### Material Design

Orange Checkboxes are based on Material Design from Google and apply Orange theming.
**Note:** Here is the full documentation
of [Material Design Checkbox](https://material.io/components/checkboxes/)

### Accessibility

Checkboxes support content labeling for accessibility and are readable by most screen readers, such
as TalkBack. Text rendered in check boxes is automatically provided to accessibility services.
Additional content labels are usually unnecessary.


### Adding a checkbox

![RadioButton](images/checkbox_light.png) ![RadioButton dark](images/checkbox_dark.png)

#### In XML

To create a Checkbox you just have to add a `Checkbox` in your layout. Orange theme will be
automatically applied

In the layout:

```xml

<CheckBox 
    android:layout_width="match_parent" 
    android:layout_height="match_parent"
    android:checked="true"
    android:text="@string/label"
/>
```

#### In Jetpack Compose

In your composable screen you can use:

```kotlin
OdsCheckbox(
    checked = true,
    onCheckedChange = { },
    enabled = true,
)
```
