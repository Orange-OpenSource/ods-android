---
layout: detail
title: Tabs
description: Tabs organize content across different screens, data sets, and other interactions.
---

**Page Summary**

* [Specifications references](#specifications-references)
* [Accessibility](#accessibility)
* [Variants](#variants)
  * [Fixed tabs](#fixed-tabs)
  * [Scrollable tabs](#scrollable-tabs)

## Specifications references

- [Design System Manager - Tabs](https://system.design.orange.com/0c1af118d/p/513d27-tabs/b/50cb71)
- [Material Design - Tabs](https://material.io/components/tabs/)

## Accessibility

Please follow [accessibility criteria for development](https://a11y-guidelines.orange.com/en/mobile/android/development/)

The Android tab components support screen reader descriptions for tabs and
badges. While optional, we strongly encourage their use.

**Content description**

Adding a content description to the entire `TabLayout` can be done in XML with
the `android:contentDescription` attribute or programmatically like so:

```kotlin
tabLayout.contentDescription = contentDescription
```

## Variants

### Fixed tabs

Fixed tabs display all tabs on one screen, with each tab at a fixed width. The
width of each tab is determined by dividing the number of tabs by the screen
width. They don’t scroll to reveal more tabs; the visible tab set represents the
only tabs available.

- - **Jetpack Compose implementation**

In Compose, the fixed tabs should be added inside of an `OdsTabRow`.
The used composable for tab depends on the type of tabs to display: classic `OdsTab` or `OdsLeadingIconTab`.

***`OdsTab` composable:***

This composable allows to display:
- an icon only tab
- a text label only tab
- a tab with an icon on top of text label

```kotlin
OdsTabRow(selectedTabIndex = pagerState.currentPage) {
    OdsTab(
        icon = painterResource(id = R.drawable.ic_alert), // if set to `null`, no icon will be displayed
        text = "Alerts", // if set to `null`, no text will be displayed
        selected = pagerState.currentPage == index,
        onClick = {
            scope.launch {
                pagerState.animateScrollToPage(index)
            }
        }
    )
    OdsTab(
        icon = painterResource(id = R.drawable.ic_calendar), // if set to `null`, no icon will be displayed
        text = "Calendar", // if set to `null`, no text will be displayed
        selected = pagerState.currentPage == index,
        onClick = {
            scope.launch {
                pagerState.animateScrollToPage(index)
            }
        }
    )
}
```

***`OdsLeadingIconTab` composable:***

This composable allows to display a tab with a text label and an icon in front of the label.

```kotlin
OdsTabRow(selectedTabIndex = pagerState.currentPage) {
    OdsLeadingIconTab(
        icon = painterResource(id = R.drawable.ic_alert), // icon is mandatory in an `OdsLeadingIconTab`
        text = "Alerts", // text is mandatory in an `OdsLeadingIconTab`
        selected = pagerState.currentPage == index,
        onClick = {
            scope.launch {
                pagerState.animateScrollToPage(index)
            }
        }
    )
    OdsLeadingIconTab(
        icon = painterResource(id = R.drawable.ic_calendar),
        text = "Calendar",
        selected = pagerState.currentPage == index,
        onClick = {
            scope.launch {
                pagerState.animateScrollToPage(index)
            }
        }
    )
}
```

- - **XML implementation**

API and source code:

* `TabLayout`: [Class definition](https://developer.android.com/reference/com/google/android/material/tabs/TabLayout), [Class source](https://github.com/material-components/material-components-android/tree/master/lib/java/com/google/android/material/tabs/TabLayout.java)
* `TabItem`: [Class definition](https://developer.android.com/reference/com/google/android/material/tabs/TabItem), [Class source](https://github.com/material-components/material-components-android/tree/master/lib/java/com/google/android/material/tabs/TabItem.java)

In the layout:

```xml
<com.google.android.material.tabs.TabLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:tabMode="fixed">

    <com.google.android.material.tabs.TabItem
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/tab_1"
        android:icon="@drawable/ic_favorite_24dp"
        />

    <com.google.android.material.tabs.TabItem
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/tab_2"
        android:icon="@drawable/ic_music_24dp"
        />

    <com.google.android.material.tabs.TabItem
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/tab_3"
        android:icon="@drawable/ic_search_24dp"
        />

</com.google.android.material.tabs.TabLayout>
```

### Scrollable tabs

Scrollable tabs are displayed without fixed widths. They are scrollable, such
that some tabs will remain off-screen until scrolled.

- - **Jetpack Compose implementation**

For scrollable tabs, the tabs should be added inside of an `OdsScrollableTabRow`. This is the only difference with fixed tabs implementation.
As for fixed tabs, you can use an `OdsTab` composable or an `OdsLeadingIconTab` inside.

```kotlin
OdsScrollableTabRow(selectedTabIndex = pagerState.currentPage) {
    OdsTab(
        icon = painterResource(id = R.drawable.ic_alert), // if set to `null`, no icon will be displayed
        text = "Alerts", // if set to `null`, no text will be displayed
        selected = pagerState.currentPage == index,
        onClick = {
            scope.launch {
                pagerState.animateScrollToPage(index)
            }
        }
    )
    OdsTab(
        icon = painterResource(id = R.drawable.ic_calendar), // if set to `null`, no icon will be displayed
        text = "Calendar", // if set to `null`, no text will be displayed
        selected = pagerState.currentPage == index,
        onClick = {
            scope.launch {
                pagerState.animateScrollToPage(index)
            }
        }
    )
}
```

- - **XML implementation**

API and source code:

*   `TabLayout`: [Class definition](https://developer.android.com/reference/com/google/android/material/tabs/TabLayout), [Class source](https://github.com/material-components/material-components-android/tree/master/lib/java/com/google/android/material/tabs/TabLayout.java)
*   `TabItem`: [Class definition](https://developer.android.com/reference/com/google/android/material/tabs/TabItem), [Class source](https://github.com/material-components/material-components-android/tree/master/lib/java/com/google/android/material/tabs/TabItem.java)

In the layout:

```xml
<com.google.android.material.tabs.TabLayout
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:tabMode="scrollable"
    app:tabContentStart="56dp">

    <com.google.android.material.tabs.TabItem
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/tab_1"
        />

    <com.google.android.material.tabs.TabItem
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/tab_2"
        />

    <com.google.android.material.tabs.TabItem
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/tab_3"
        />

    <com.google.android.material.tabs.TabItem
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/tab_4"
        />

    <com.google.android.material.tabs.TabItem
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/tab_5"
        />

</com.google.android.material.tabs.TabLayout>
```
