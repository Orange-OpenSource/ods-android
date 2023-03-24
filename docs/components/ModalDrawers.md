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
    drawerHeader = {
        title = "Side navigation drawer" // title is mandatory in an `OdsModalDrawer`
        imageContentDescription = "" // optional
        imageDisplayType =
            OdsModalDrawerHeaderImageDisplayType.None // or OdsModalDrawerHeaderImageDisplayType.Avatar or OdsModalDrawerHeaderImageDisplayType.Background
        subtitle = "Example" // optional
        image =
            painterResource(id = R.drawable.placeholder)
    },
    drawerContentList = mutableListOf<OdsModalDrawerItem>(
        OdsModalDrawerListItem( // `OdsModalDrawerListItem` is used to specified an item of the list
            icon = R.drawable.ic_heart,
            text = "label1"
        ),
        OdsModalDrawerListItem(
            icon = R.drawable.ic_heart,
            text = "label2"
        ),
        OdsModalDrawerDivider, // `OdsModalDrawerDivider` is used to add a divider in a specific level of the list
        OdsModalDrawerSectionLabel(
            label = "Label"
        ), // `OdsModalDrawerSectionLabel` is used to add a divider and the text above the divider
        OdsModalDrawerListItem(
            icon = R.drawable.ic_heart,
            text = "label3"
        )
    ),
    drawerState = rememberDrawerState(DrawerValue.Closed),// or rememberDrawerState(DrawerValue.Open)
) {
    // The content of the rest of the UI
}
```

## Component specific tokens

_Soon available_