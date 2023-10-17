---
layout: detail
title: "Navigation: bottom"
description: Bottom navigation bars allow movement between primary destinations in an app.
---

<br>**On this page**

* [Specifications references](#specifications-references)
* [Accessibility](#accessibility)
* [Implementation](#implementation)
    * [Jetpack Compose](#jetpack-compose)
        * [OdsBottomNavigation API](#odsbottomnavigation-api)

---

## Specifications references

- [Design System Manager - Navigation: bottom](https://system.design.orange.com/0c1af118d/p/042eb8-navigation-bottom/b/30078d)
- [Material Design - Bottom navigation](https://material.io/components/bottom-navigation)

## Accessibility

Please follow [accessibility criteria for development](https://a11y-guidelines.orange.com/en/mobile/android/development/).

Note that TalkBack already reads the bottom navigation items labels so the content descriptions of the `OdsBottomNavigationItemIcon`s can be empty.

## Implementation

![BottomNavigation light](images/bottom_navigation_light.png)

![BottomNavigation dark](images/bottom_navigation_dark.png)

### Jetpack Compose

In your composable screen, use the `OdsBottomNavigation` composable. It should contain multiple `OdsBottomNavigationItem`s.

Here is an example of use:

```kotlin
    private data class NavigationItem(
    @StringRes val titleResId: Int,
    @DrawableRes val iconResId: Int
)

val items = listOf(
    R.string.component_bottom_navigation_coffee to R.drawable.ic_coffee,
    R.string.component_bottom_navigation_cooking_pot to R.drawable.ic_cooking_pot,
    R.string.component_bottom_navigation_ice_cream to R.drawable.ic_ice_cream,
    R.string.component_bottom_navigation_restaurant to R.drawable.ic_restaurant,
    R.string.component_bottom_navigation_favorites to R.drawable.ic_heart
)

var selectedItemIndex by remember { mutableStateOf(0) }

OdsBottomNavigation(
    items = items.mapIndexed { index, item ->
        OdsBottomNavigationItem(
            icon = OdsBottomNavigationItemIcon(
                painter = painterResource(id = item.first),
                contentDescription = ""
            ), // contentDescription is empty cause TalkBack already read the item's label
            label = stringResource(id = item.second),
            selected = selectedItemIndex == index,
            onClick = {
                selectedItemIndex = index
                doSomething()
            }
        )
    }
)
```

#### OdsBottomNavigation API

Parameter | Default&nbsp;value | Description
-- | -- | --
`items: List<OdsBottomNavigationItem>` | | Items displayed into the bottom navigation
`modifier: Modifier` | `Modifier` | `Modifier` applied to the bottom navigation
{:.table}
