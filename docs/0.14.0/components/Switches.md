---
layout: detail
title: Switches
description: Switch selection control allows the user to select options.
---

Switches toggle the state of a single setting on or off. They are the preferred
way to adjust settings on mobile.

---

**Page Summary**

* [Specifications references](#specifications-references)
* [Accessibility](#accessibility)
* [Implementation](#implementation)
* [Component specific tokens](#component-specific-tokens)

---

## Specifications references

- [Design System Manager - Selection controls](https://system.design.orange.com/0c1af118d/p/14638a-selection-controls/b/352c00)
- [Material Design - Switches](https://material.io/components/switches)
- Technical documentation soon available

## Accessibility

Please follow [accessibility criteria for development](https://a11y-guidelines.orange.com/en/mobile/android/development/)

Switches support content labeling for accessibility and are readable by most
screen readers, such as TalkBack. Text rendered in switches is automatically
provided to accessibility services. Additional content labels are usually
unnecessary.

### Implementation

![Switch](images/switch_light.png) ![Switch dark](images/switch_dark.png)

> **Jetpack Compose implementation**

In your composable screen you can use:

```kotlin
OdsSwitch(
    checked = true,
    onCheckedChange = {
        // Do something
    },
    enabled = true
)
```

> **XML implementation**

To create a Switch using Orange theme you will have to add `com.google.android.material.switchmaterial.SwitchMaterial` into your layout.

API and source code:

*   `SwitchMaterial`: [Class definition](https://developer.android.com/reference/com/google/android/material/switchmaterial/SwitchMaterial), [Class source](https://github.com/material-components/material-components-android/tree/master/lib/java/com/google/android/material/switchmaterial/SwitchMaterial.java)

In the layout:

```xml
<com.google.android.material.switchmaterial.SwitchMaterial
    android:layout_width="wrap_content"
    android:layout_height="match_parent"
    android:checked="true"
    android:text="@string/label_1"
/>
<com.google.android.material.switchmaterial.SwitchMaterial
    android:layout_width="wrap_content"
    android:layout_height="match_parent"
    android:text="@string/label_2"
/>
<com.google.android.material.switchmaterial.SwitchMaterial
    android:layout_width="wrap_content"
    android:layout_height="match_parent"
    android:text="@string/label_3"
/>
<com.google.android.material.switchmaterial.SwitchMaterial
    android:layout_width="wrap_content"
    android:layout_height="match_parent"
    android:text="@string/label_4"
/>
<com.google.android.material.switchmaterial.SwitchMaterial
    android:layout_width="wrap_content"
    android:layout_height="match_parent"
    android:enabled="false"
    android:text="@string/label_5"
/>
```

In code:

```kotlin
// To check a switch
switchMaterial.isChecked = true

// To listen for a switch's checked/unchecked state changes
switchMaterial.setOnCheckedChangeListener { buttonView, isChecked
    // Responds to switch being checked/unchecked
}
```

## Component specific tokens

_Soon available_
