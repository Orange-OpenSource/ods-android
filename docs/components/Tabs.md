---
layout: detail
title: Tabs
description: Tabs organize content across different screens, data sets, and other interactions.
---

<br>**On this page**

* [Specifications references](#specifications-references)
* [Accessibility](#accessibility)
* [Variants](#variants)
    * [Fixed tabs row](#fixed-tabs-row)
        * [Jetpack Compose](#jetpack-compose)
            * [OdsTabRow API](#odstabrow-api)
    * [Scrollable tabs row](#scrollable-tabs-row)
        * [Jetpack Compose](#jetpack-compose-1)
            * [OdsScrollableTabRow API](#odsscrollabletabrow-api)
* [Extras](#extras)
    * [Jetpack Compose](#jetpack-compose-2)
        * [Tab](#tab)
            * [OdsTab API](#odstab-api)
        * [Leading icon tab](#leading-icon-tab)
            * [OdsLeadingIconTab API](#odsleadingicontab-api)

---

## Specifications references

- [Design System Manager - Tabs](https://system.design.orange.com/0c1af118d/p/513d27-tabs/b/50cb71)
- [Material Design - Tabs](https://material.io/components/tabs/)

## Accessibility

Please follow [accessibility criteria for development](https://a11y-guidelines.orange.com/en/mobile/android/development/).

The Android tab components support screen reader descriptions for tabs and
badges. While optional, we strongly encourage their use.

## Variants

### Fixed tabs row

Fixed tabs display all tabs on one screen, with each tab at a fixed width. The
width of each tab is determined by dividing the number of tabs by the screen
width. They don’t scroll to reveal more tabs; the visible tab set represents the
only tabs available.

#### Jetpack Compose

To display fixed tabs, use `OdsTabRow` composable and provide a list of `OdsTabRowTab` representing the tabs to display.  
According to the `leadingIconTabs` value, the composable will display classic `OdsTab` (icon above text) or `OdsLeadingIconTab` (icon before text).  
For more information, see [Extras](#extras) section.

![Fixed tabs light](images/tabs_fixed_light.png)

![Fixed tabs dark](images/tabs_fixed_dark.png)

```kotlin
OdsTabRow(
    selectedTabIndex = 0,
    tabs = listOf(
        OdsTabRowTab(
            painter = painterResource(id = R.drawable.ic_heart),
            text = "Favourites",
            selected = true,
            onClick = { doSomething() }
        ),
        OdsTabRowTab(
            painter = painterResource(id = R.drawable.ic_call),
            text = "Calls",
            selected = false,
            onClick = { doSomething() }
        )
    ),
    leadingIconTabs = false
)
```

##### OdsTabRow API

Parameter | Default&nbsp;value | Description
-- | -- | --
`selectedTabIndex: Int` | | Index of the currently selected tab
`tabs: List<OdsTabRowTab>` | | List of the `OdsTabRowTab` displayed inside this tabs row
`modifier: Modifier` | `Modifier` | `Modifier` applied to the tabs row
`leadingIconTabs: Boolean` | `false` | Controls the composable used to render the tabs: `OdsLeadingIconTab` or `OdsTab`
{:.table}

### Scrollable tabs row

Scrollable tabs are displayed without fixed widths. They are scrollable, such
that some tabs will remain off-screen until scrolled.

![Scrollable tabs light](images/tabs_scrollable_light.png)

![Scrollable tabs dark](images/tabs_scrollable_dark.png)

#### Jetpack Compose

To display scrollable tabs, use `OdsScrollableTabRow` composable. This is the only difference with fixed tabs implementation.  
As for fixed tabs, the composable will display classic `OdsTab` (icon above text) or `OdsLeadingIconTab` (icon before text) depending on the `leadingIconTabs` value.  
For more information, see [Extras](#extras) section.

```kotlin
OdsScrollableTabRow(
    selectedTabIndex = 0,
    tabs = listOf(
        OdsTabRowTab(
            painter = painterResource(id = R.drawable.ic_heart),
            text = "Favourites",
            selected = true,
            onClick = { doSomething() }
        ),
        OdsTabRowTab(
            painter = painterResource(id = R.drawable.ic_call),
            text = "Calls",
            selected = false,
            onClick = { doSomething() }
        )
    ),
    leadingIconTabs = false
)
```

##### OdsScrollableTabRow API

Parameter | Default&nbsp;value | Description
-- | -- | --
`selectedTabIndex: Int` | | Index of the currently selected tab
`tabs: List<OdsTabRowTab>` | | List of the `OdsTabRowTab` displayed inside this tabs row
`modifier: Modifier` | `Modifier` | `Modifier` applied to the scrollable tabs row
`leadingIconTabs: Boolean` | `false` | Controls the composable used to render the tabs: `OdsLeadingIconTab` or `OdsTab`
{:.table}

## Extras

### Jetpack Compose

The used composables for tabs row depends on the type of tabs to display: classic `OdsTab` or `OdsLeadingIconTab`.

#### Tab

This composable allows to display:

- an icon only tab
- a text label only tab
- a tab with an icon on top of text label

```kotlin
  OdsTab(
    icon = OdsTabIcon(painterResource(id = R.drawable.ic_alert)),
    text = "Alerts",
    selected = false,
    onClick = { doSomething() }
)
```

##### OdsTab API

Parameter | Default&nbsp;value | Description
-- | -- | --
`selected: Boolean` | | Controls whether the tab is selected or not
`onClick: () -> Unit` | | Callback invoked on tab click, when the tab is selected
`modifier: Modifier` | `Modifier` | `Modifier` applied to the tab
`enabled: Boolean` | `true` | Controls the enabled state of the tab. When `false`, the tab will not be clickable and will appear disabled to accessibility services.
`text: String?` | `null` | Text label displayed in the tab. Always displayed in uppercase
`icon: OdsTabIcon?` | `null` | Icon displayed in the tab
{:.table}

#### Leading icon tab

This composable allows to display a tab with a text label and an icon in front of the label.

```kotlin
  OdsLeadingIconTab(
    icon = OdsLeadingIconTabIcon(painterResource(id = R.drawable.ic_alert)),
    text = "Alerts",
    selected = false,
    onClick = { doSomething() }
)
```

##### OdsLeadingIconTab API

Parameter | Default&nbsp;value | Description
-- | -- | --
`selected: Boolean` | | Controls whether the tab is selected or not
`icon: OdsLeadingIconTabIcon` | | Icon displayed in the tab
`text: String` | | Text label displayed in the tab. Always displayed in uppercase
`onClick: () -> Unit` | | Callback invoked on tab click, when the tab is selected
`modifier: Modifier` | `Modifier` | `Modifier` applied to the tab
`enabled: Boolean` | `true` | Controls the enabled state of the tab. When `false`, the tab will not be clickable and will appear disabled to accessibility services.
{:.table}