---
layout: detail
title: Navigation drawers
description: The navigation drawer slides in from the left when the nav icon is tapped. The content should be concerned with identity and/or navigation..
---

---

**Page Summary**

* [Specifications references](#specifications-references)
* [Accessibility](#accessibility)
* [Implementation](#implementation)
* [Component specific tokens](#component-specific-tokens)

---

## Specifications references

- [Design System Manager - Navigation drawers](https://system.design.orange.com/0c1af118d/p/92bc26-navigation-drawers/b/146f55)
- [Material Design - Navigation drawer](https://m2.material.io/components/navigation-drawer)
- Technical documentation soon available

## Accessibility

Please follow [accessibility criteria for development](https://a11y-guidelines.orange.com/en/mobile/android/development/)

## Implementation

> **Jetpack Compose implementation**

You can use the `OdsModalDrawer` composable like this:

```kotlin
OdsModalDrawer(
    drawerState = rememberDrawerState(DrawerValue.Closed),// or rememberDrawerState(DrawerValue.Open)
    content = {
        // Do something
    },
    headerParametersProvider = {
        // Do something
    },
    listContent = {
        // Do something
    },
)
```

## Component specific tokens

_Soon available_