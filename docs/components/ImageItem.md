---
layout: detail
title: Image item
description:
---

---

**Page Summary**

* [Specifications references](#specifications-references)
* [Accessibility](#accessibility)
* [Implementation](#implementation)
* [Component specific tokens](#component-specific-tokens)

---

## Specifications references

- [Design System Manager - Image Item](https://system.design.orange.com/0c1af118d/p/49434d-image-item)
- [Material Design - Image lists](https://m2.material.io/components/image-lists)
- Technical documentation soon available

## Accessibility

Please follow [accessibility criteria for development](https://a11y-guidelines.orange.com/en/mobile/android/development/)

## Implementation

> **Jetpack Compose implementation**

You can use the `OdsImageItem` composable like this:

```kotlin
OdsImageItem(
    image = painterResource(id = R.drawable.placeholder),
    iconChecked = false,
    iconSelected = true,
    onIconCheckedChange = { },
    iconContentDescription = "", // Optional
    checkedIcon = painterResource(id = R.drawable.ic_heart),
    uncheckedIcon = painterResource(id = R.drawable.ic_heart_outlined),
    displayType = OdsImageItemDisplayTitle.Overlay,
    modifier = modifier,
    imageContentDescription = "Picture content description", //Optional
    title = "Component Image Item" // Optional 
)
```

## Component specific tokens

_Soon available_