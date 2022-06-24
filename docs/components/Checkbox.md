---
layout: detail
title: Checkbox
description: Checkbox selection control allows the user to select options.
---

---

**Page Summary**

* [Specifications references](#specifications-references)
* [Accessibility](#accessibility)
* [Implementation](#implementation)
* [Component specific tokens](#component-specific-tokens)

---

Use checkboxes to:
* Select one or more options from a list
* Present a list containing sub-selections
* Turn an item on or off in a desktop environment

## Specifications references

- [Design System Manager - Selection controls](https://system.design.orange.com/0c1af118d/p/14638a-selection-controls/b/352c00)
- [Material Design - Checkboxes](https://material.io/components/checkboxes/)
- Technical documentation soon available

## Accessibility

Please follow [accessibility criteria for development](https://a11y-guidelines.orange.com/en/mobile/android/development/)

Checkboxes support content labeling for accessibility and are readable by most screen readers, such
as TalkBack. Text rendered in check boxes is automatically provided to accessibility services.
Additional content labels are usually unnecessary.

### Implementation

![Checkbox](images/checkbox_light.png) ![Checkbox dark](images/checkbox_dark.png)

> **Jetpack Compose implementation**

In your composable screen you can use:

```kotlin
OdsCheckbox(
    checked = true,
    onCheckedChange = { },
    enabled = true,
)
```

> **XML implementation**

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

## Component specific tokens

_Soon available_
