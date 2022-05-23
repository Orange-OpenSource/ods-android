---
layout: detail
title: Bottom navigation
description: Bottom navigation description
---

# Bottom navigation

Bottom navigation bars allow movement between primary destinations in an app.

**Contents**

* [Using bottom navigation](#using-bottom-navigation)
  *   [Material Design](#material-design)
  *   [Accessibility](#accessibility)
  *   [Bottom navigation bar example](#bottom-navigation-bar-example)
        *   [In XML](#in-xml)
        *   [In JetPack Compose](#in-jetpack-compose)

## Using bottom navigation

Before you can use Orange themed bottom navigation, you need to add a dependency to the Orange Design System
for Android library. For more information, go to the
[Getting started](../home_content.md) page.

### Material Design

Orange Bottom Navigation are based on Material Design from Google and apply Orange theming.
**Note:** Here is the full documentation
of [Material Design Bottom Navigation](https://material.io/components/bottom-navigation/)

### Accessibility

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

### Bottom navigation bar example

#### In XML

API and source code:

*   `BottomNavigationView`
    *   [Class description](https://developer.android.com/reference/com/google/android/material/bottomnavigation/BottomNavigationView)
    *   [Class source](https://github.com/material-components/material-components-android/tree/master/lib/java/com/google/android/material/bottomnavigation/BottomNavigationView.java)

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

```kt
bottomNavigation.selectedItemId = R.id.page_2
```

#### In Jetpack Compose

In your composable screen, use the `OdsBottomNavigation` composable. It should contain multiple `OdsBottomNavigationItem`s.

Here is an example:

```kotlin
    val navigationItems = listOf(
        NavigationItem("Favorites", R.drawable.ic_heart),
        NavigationItem("Search", R.drawable.ic_search),
        NavigationItem("Information", R.drawable.ic_info),
        NavigationItem("Notification", R.drawable.ic_notification),
        NavigationItem("Settings", R.drawable.ic_settings)
    )

    val selectedItem = remember { mutableStateOf(navigationItems[0]) }

    OdsBottomNavigation {
        for (item in navigationItems) {
            OdsBottomNavigationItem(
                icon = { Icon(painter = painterResource(id = item.icon), contentDescription = null) }, // contentDescription is null cause Talkback already read the item's title
                label = item.title,
                selected = selectedItem.value.title == item.title,
                onClick = {
                    selectedItem.value = item
                    // Do what you want with a piece of code
                }
            )
        }
    }
```