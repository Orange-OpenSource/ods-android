---
layout: detail
title: Menus
description: Menus appear from a button, action, or other control. It contains at least 2 items that can affect the app, the view or elements within the view.
---

---

**Page Summary**

* [Specifications references](#specifications-references)
* [Accessibility](#accessibility)
* [Variants](#variants)
    * [Dropdown menu](#dropdown-menu)
    * [Exposed dropdown menu](#exposed-dropdown-menu)
* [Component specific tokens](#component-specific-tokens)

---

## Specifications references

- [Design System Manager - Menus](https://system.design.orange.com/0c1af118d/p/07a69b-menus/b/862cbb)
- [Material Design - Menus](https://m2.material.io/components/menus)
- Technical documentation soon available

## Accessibility

Please follow [accessibility criteria for development](https://a11y-guidelines.orange.com/en/mobile/android/development/)

## Variants

### Dropdown menu

A dropdown menu is a compact way of displaying multiple choices. It appears upon interaction with an element (such as an icon or button) or when users perform a specific action.

![Dropdown menu light](images/menu_dropdown_light.png) ![Dropdown menu dark](images/menu_dropdown_dark.png)

> **Jetpack Compose implementation**

The library offers an `OdsDropdownMenu` container composable in which you can add `OdsDropdownMenuItem` or `OdsDivider` as shown in the following example:

```kotlin
var menuExpanded by remember { mutableStateOf(false) }

OdsDropdownMenu(
    expanded = menuExpanded,
    onDismissRequest = { menuExpanded = false },
    offset = DpOffset(x = (-100).dp, y = (-10).dp)
) {
    OdsDropdownMenuItem(
        text = "Summer salad",
        icon = painterResource(id = R.drawable.ic_salad),
        onClick = {
            // Do something
        }
    )
    OdsDivider() // Allow to add a divider between the 2 items
    OdsDropdownMenuItem(
        text = "Brocoli soup",
        icon = painterResource(id = R.drawable.ic_soup),
        onClick = {
            // Do something
        }
    )
}
```

> **XML implementation**

*Not available yet*

### Exposed dropdown menu

Exposed dropdown menus display the currently selected menu item above the menu. This is a combination of a text field and a menu.

![Exposed dropdown menu light](images/menu_exposed_dropdown_light.png)  ![Exposed dropdown menu dark](images/menu_exposed_dropdown_dark.png)

> **Jetpack Compose implementation**

To display an exposed dropdown menu, you can use the `OdsExposedDropdownMenu` composable. As shown below, you should provide a list of `OdsExposedDropdownMenuItem` corresponding to the items displayed in the menu (with or without icons).

```kotlin
val items = listOf(
    OdsExposedDropdownMenuItem("Email", android.R.drawable.ic_dialog_email),
    OdsExposedDropdownMenuItem("Map", android.R.drawable.ic_dialog_map),
    OdsExposedDropdownMenuItem("Dialer", android.R.drawable.ic_dialog_dialer),
)
val selectedItem = rememberSaveable() { mutableStateOf(items.first()) }

OdsExposedDropdownMenu(
    label = "Dropdown menu label",
    items = items,
    selectedItem = selectedItem,
    onItemSelectionChange = { item ->
        // Do something like retrieving the selected item
    },
    enabled = true
)
```

> **XML implementation**

*Not available yet*

## Component specific tokens

_Soon available_
