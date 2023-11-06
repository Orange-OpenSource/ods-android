---
layout: detail
title: Tabs
description: Tabs organize content across different screens, data sets, and other interactions.
---

<br>**On this page**

* [Specifications references](#specifications-references)
* [Accessibility](#accessibility)
* [Variants](#variants)
    * [Fixed tabs](#fixed-tabs)
        * [Jetpack Compose](#jetpack-compose)
    * [Scrollable tabs](#scrollable-tabs)
        * [Jetpack Compose](#jetpack-compose-1)

---

## Specifications references

- [Design System Manager - Tabs](https://system.design.orange.com/0c1af118d/p/513d27-tabs/b/50cb71)
- [Material Design - Tabs](https://material.io/components/tabs/)

## Accessibility

Please follow [accessibility criteria for development](https://a11y-guidelines.orange.com/en/mobile/android/development/).

The Android tab components support screen reader descriptions for tabs and
badges. While optional, we strongly encourage their use.

## Variants

### Fixed tabs

Fixed tabs display all tabs on one screen, with each tab at a fixed width. The
width of each tab is determined by dividing the number of tabs by the screen
width. They don’t scroll to reveal more tabs; the visible tab set represents the
only tabs available.

#### Jetpack Compose

In Compose, the fixed tabs should be added inside of an `OdsTabRow`.
The used composable for tab depends on the type of tabs to display: classic `OdsTab` or `OdsLeadingIconTab`.

![Fixed tabs light](images/tabs_fixed_light.png)

![Fixed tabs dark](images/tabs_fixed_dark.png)

**`OdsTab` composable:**

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

**`OdsLeadingIconTab` composable:**

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

### Scrollable tabs

Scrollable tabs are displayed without fixed widths. They are scrollable, such
that some tabs will remain off-screen until scrolled.

![Scrollable tabs light](images/tabs_scrollable_light.png)

![Scrollable tabs dark](images/tabs_scrollable_dark.png)

#### Jetpack Compose

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
