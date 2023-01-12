---
layout: detail
title: "Navigation: bottom"
description: Bottom navigation bars allow movement between primary destinations in an app.
---

---

**Page Summary**

* [Specifications references](#specifications-references)
* [Accessibility](#accessibility)
* [Implementation](#implementation)
* [Component specific tokens](#component-specific-tokens)

---

## Specifications references

- [Design System Manager - Navigation: bottom](https://system.design.orange.com/0c1af118d/p/042eb8-navigation-bottom/b/30078d)
- [Material Design - Bottom navigation](https://material.io/components/bottom-navigation)
- Technical documentation soon available

## Accessibility

Please follow [accessibility criteria for development](https://a11y-guidelines.orange.com/en/mobile/android/development/)

You should set an `android:title` for each of your `menu` items so that screen
readers like TalkBack can properly announce what each navigation item
represents:

```xml
<menu xmlns:android="http://schemas.android.com/apk/res/android">
  <item
      android:title="@string/text_label"/>
</menu>
```

The `labelVisibilityMode` attribute can be used to adjust the behavior of the
text labels for each navigation item. There are four visibility modes:

*   `LABEL_VISIBILITY_AUTO` (default): The label behaves as “labeled” when there
    are 3 items or less, or “selected” when there are 4 items or more
*   `LABEL_VISIBILITY_SELECTED`: The label is only shown on the selected
    navigation item
*   `LABEL_VISIBILITY_LABELED`: The label is shown on all navigation items
*   `LABEL_VISIBILITY_UNLABELED`: The label is hidden for all navigation items

## Implementation

  ![BottomNavigation light](images/bottom_navigation_light.png)

  ![BottomNavigation dark](images/bottom_navigation_dark.png)

> **Jetpack Compose implementation**

In your composable screen, use the `OdsBottomNavigation` composable. It should contain multiple `OdsBottomNavigationItem`s.

Here is an example:

```kotlin
    private data class NavigationItem(@StringRes val titleResId: Int, @DrawableRes val iconResId: Int)

    val navigationItems = listOf(
        NavigationItem(R.string.component_bottom_navigation_coffee, R.drawable.ic_coffee),
        NavigationItem(R.string.component_bottom_navigation_cooking_pot, R.drawable.ic_cooking_pot),
        NavigationItem(R.string.component_bottom_navigation_ice_cream, R.drawable.ic_ice_cream),
        NavigationItem(R.string.component_bottom_navigation_restaurant, R.drawable.ic_restaurant),
        NavigationItem(R.string.component_bottom_navigation_favorites, R.drawable.ic_heart)
    )

    val selectedItem = remember { mutableStateOf(navigationItems[0]) }

    OdsBottomNavigation {
        for (item in navigationItems) {
            OdsBottomNavigationItem(
                icon = { Icon(painter = painterResource(id = item.iconResId), contentDescription = null) }, // contentDescription is null cause Talkback already read the item's title
                label = stringResource(id = item.titleResId),
                selected = selectedItem.value.titleResId == item.titleResId,
                onClick = {
                    selectedItem.value = item
                    // Do what you want with a piece of code
                }
            )
        }
    }
```

> **XML implementation**

API and source code:

*   `BottomNavigationView`: [Class description](https://developer.android.com/reference/com/google/android/material/bottomnavigation/BottomNavigationView), [Class source](https://github.com/material-components/material-components-android/tree/master/lib/java/com/google/android/material/bottomnavigation/BottomNavigationView.java)

In `layout.xml`:

```xml
<LinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="wrap_content">

  <com.google.android.material.bottomnavigation.BottomNavigationView
      android:id="@+id/bottom_navigation"
      android:layout_width="match_parent"
      android:layout_height="wrap_content"
      app:menu="@menu/bottom_navigation_menu" />

</LinearLayout>
```

In `bottom_navigation_menu.xml` inside a `menu` resource directory:

```xml
<menu xmlns:android="http://schemas.android.com/apk/res/android">
  <item
      android:id="@+id/page_1"
      android:enabled="true"
      android:icon="@drawable/ic_favorite"
      android:title="@string/favorites"/>
  <item
      android:id="@+id/page_2"
      android:enabled="true"
      android:icon="@drawable/ic_music"
      android:title="@string/music"/>
  <item
      android:id="@+id/page_3"
      android:enabled="true"
      android:icon="@drawable/ic_places"
      android:title="@string/places"/>
  <item
      android:id="@+id/page_4"
      android:enabled="true"
      android:icon="@drawable/ic_news"
      android:title="@string/news"/>
</menu>
```

In code:

```kotlin
bottomNavigation.selectedItemId = R.id.page_2
```

## Component specific tokens

_Soon available_
