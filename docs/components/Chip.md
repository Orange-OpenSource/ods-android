---
layout: detail
title: Chip
description: Chips are compact elements that represent an input, attribute, or action.
---

**Contents**

* [Using chips](#using-chips)
  *   [Material Design](#material-design)
  *   [Accessibility](#accessibility)
  *   [Types](#types)
        * [Input chip](#input-chip)
        * [Choice chip](#choice-chip)
        * [Filter chip](#filter-chip)
        * [Action chip](#action-chip)

## Using chips

Before you can use Orange themed chips, you need to add a dependency to the Orange Design System for
Android library. For more information, go to the
[Getting started](../home_content.md) page.

Chips allow users to enter information, make selections, filter content, or trigger actions. While
buttons are expected to appear consistently and with familiar calls to action, chips should appear
dynamically as a group of multiple interactive elements.

### Material Design

Orange Chips are based on Material Design from Google and apply Orange theming.
**Note:** Here is the full documentation
of [Material Design Card](https://material.io/components/chips/)

### Accessibility

Chips support content labeling for accessibility and are readable by most screen readers, such as
TalkBack. Text rendered in chips is automatically provided to accessibility services. Additional
content labels are usually unnecessary.

### Types

There are four types of chips:

- [Input chip](#input-chip)
- [Choice chip](#choice-chip)
- [Filter chip](#filter-chip)
- [Action chip](#action-chip)

API and source code:

* `Chip`
    * [Class definition](https://developer.android.com/reference/com/google/android/material/chip/Chip)
    * [Class source](https://github.com/material-components/material-components-android/tree/master/lib/java/com/google/android/material/chip/Chip.java)
* `ChipGroup`
    * [Class definition](https://developer.android.com/reference/com/google/android/material/chip/ChipGroup)
    * [Class source](https://github.com/material-components/material-components-android/tree/master/lib/java/com/google/android/material/chip/ChipGroup.java)
* `ChipDrawable`
    * [Class definition](https://developer.android.com/reference/com/google/android/material/chip/ChipDrawable)
    * [Class source](https://github.com/material-components/material-components-android/tree/master/lib/java/com/google/android/material/chip/ChipDrawable.java)

## Input chip

Input chips (refered to as **entry** chips in Android) represent a complex piece of information in
compact form, such as an entity (person, place, or thing) or text. They enable user input and verify
that input by converting text into chips.

To create an input chip you must add a `com.google.android.material.chip.Chip` component to your
layout and set `style` property to `@style/Widget.MaterialComponents.Chip.Entry`

In the layout:

```xml

<com.google.android.material.chip.ChipGroup>
    <com.google.android.material.chip.Chip 
        android:id="@+id/chip_1"
        style="@style/Widget.MaterialComponents.Chip.Entry"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content" 
        android:text="@string/text_input_1" />

</com.google.android.material.chip.ChipGroup>
```

## Choice chip

Choice chips allow selection of a single chip from a set of options.

Choice chips clearly delineate and display options in a compact area. They are a good alternative to
toggle buttons, radio buttons, and single select menus.

To create a choice chip you must add a `com.google.android.material.chip.Chip` component to your
layout and set `style` property to `@style/Widget.MaterialComponents.Chip.Choice`

In the layout:

```xml

<com.google.android.material.chip.ChipGroup...>
    <com.google.android.material.chip.Chip
        android:id="@+id/chip_1"
        style="@style/Widget.MaterialComponents.Chip.Choice"
        android:layout_width="wrap_content" 
        android:layout_height="wrap_content"
        android:text="@string/text_choice_1" />

    </com.google.android.material.chip.ChipGroup>
```

## Filter chip

Filter chips use tags or descriptive words to filter content.

Filter chips clearly delineate and display options in a compact area. They are a good alternative to
toggle buttons or checkboxes.

To create a filter chip you must add a `com.google.android.material.chip.Chip` component to your
layout and set `style` property to `@style/Widget.MaterialComponents.Chip.Filter`

In the layout:

```xml

<com.google.android.material.chip.ChipGroup...>
    <com.google.android.material.chip.Chip
        android:id="@+id/chip_1"
        style="@style/Widget.MaterialComponents.Chip.Filter"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/text_choice_1" />

</com.google.android.material.chip.ChipGroup>
```

## Action chip

Action chips offer actions related to primary content. They should appear dynamically and
contextually in a UI.

An alternative to action chips are buttons, which should appear persistently and consistently.

To create an action chip you must add a `com.google.android.material.chip.Chip` component to your
layout and set `style` property to `@style/Widget.MaterialComponents.Chip.Action`

In the layout:

```xml

<com.google.android.material.chip.ChipGroup...>
    <com.google.android.material.chip.Chip
        android:id="@+id/chip_1"
        style="@style/Widget.MaterialComponents.Chip.Action"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/text_choice_1" />

</com.google.android.material.chip.ChipGroup>
```