---
layout: detail
title: "App bars: top"
description: Top app bars display information and actions relating to the current screen.
---

<br>**On this page**

* [Specifications references](#specifications-references)
* [Accessibility](#accessibility)
* [Variants](#variants)
    * [Regular top app bar](#regular-top-app-bar)
    * [Large top app bar](#large-top-app-bar)
* [Component specific tokens](#component-specific-tokens)

---

## Specifications references

- [Design System Manager - App bars](https://system.design.orange.com/0c1af118d/p/23e0e6-app-bars/b/620966)
- [Material Design - App bars: top](https://material.io/components/app-bars-top/)

## Accessibility

Please follow [accessibility criteria for development](https://a11y-guidelines.orange.com/en/mobile/android/development/)

`OdsTopAppBar` provides accessibility support for the navigation icon,
action items, overflow menu and more for informing the user as to what each
action performs.

## Variants

### Regular top app bar

#### Jetpack Compose implementation

Add `OdsTopAppBar` composable to your Scaffold `topBar`.
Here is an example of use:

```kotlin
OdsTopAppBar(
    title = "Title",
    navigationIcon = OdsTopAppBarNavigationIcon(
        painter = painterResource(id = R.drawable.ic_back),
        contentDescription = "content description",
        onClick = { doSomething() }
    ),
    actions = listOf(
        OdsTopAppBarActionButton(
            painter = painterResource(id = R.drawable.ic_share),
            contentDescription = "content description",
            onClick = { doSomething() }
        ),
        // ...
    ),
    overflowMenuActions = listOf(
        OdsTopAppBarOverflowMenuActionItem(
            text = "Text",
            onClick = { doSomething() }
        ),
        // ...
    )
)
```

Note: By default, the `OdsTopAppBar` is elevated but you can set `elevated` parameter to `false` if you don't want any shadow below it (for example if you want to display tabs below).
{:.bd-callout .bd-callout-info}

##### OdsTopAppBar API

Parameter | Default&nbsp;value | Description
-- | -- | --
`title: String` | | Title to be displayed in the center of the top app bar
`modifier: Modifier` | `Modifier` |`Modifier` to be applied to the top app bar
`navigationIcon: OdsTopAppBarNavigationIcon?` | `null` | Icon to be displayed at the start of the top app bar
`actions: List<OdsTopAppBarActionButton>` | `emptyList()` | Actions to be displayed at the end of the top app bar. The default layout here is a `Row`, so icons inside will be placed horizontally.
`overflowMenuActions: List<OdsTopAppBarOverflowMenuActionItem>` | `emptyList()` | Actions to be displayed in the overflow menu
`elevated: Boolean` | `true` | Controls the elevation of the top app bar: `true` to set an elevation to the top app bar (a shadow is displayed below), `false` otherwise
{:.table}

#### XML implementation

_Not available_

### Large top app bar

#### Jetpack Compose implementation

First, you have to add this line in your application `build.gradle.kts` file cause this component relies on Compose Material 3 implementation:

```kotlin
implementation("androidx.compose.material3:material3:<version number>")
```

Then you can add `OdsLargeTopAppBar` composable to your Scaffold `topBar`:

```kotlin
OdsLargeTopAppBar(
    title = "Title",
    navigationIcon = OdsTopAppBarNavigationIcon(
        painter = painterResource(id = R.drawable.ic_back),
        contentDescription = "content description",
        onClick = { doSomething() }
    ),
    actions = listOf(
        OdsTopAppBarActionButton(
            painter = painterResource(id = R.drawable.ic_share),
            contentDescription = "content description",
            onClick = { doSomething() }
        ),
        // ...
    ),
    overflowMenuActions = listOf(
        OdsTopAppBarOverflowMenuActionItem(
            text = "Text",
            onClick = { doSomething() }
        ),
        // ...
    ),
    scrollBehavior = null // See below to attach a scroll behavior and make the top app bar collapsible
)
```

If you want a collapsible large top app bar, you can follow these steps:

1 - Define the scroll behavior to use:

```kotlin
val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())
```

2 - Provide this `scrollBehavior` to the `OdsLargeTopAppBar` and as a modifier of your Scaffold in order to listen to the scroll event

```kotlin
Scaffold(
    modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
    topBar = {
        OdsLargeTopAppBar(
            //...
            scrollBehavior = scrollBehavior,
        )
    },
    //...
) {
    // Scaffold content
}
```

##### OdsLargeTopAppBar API

Parameter | Default&nbsp;value | Description
-- | -- | --
`title: String` | | Title displayed in the center of the top app bar
`modifier: Modifier` | `Modifier` |`Modifier` applied to the top app bar
`navigationIcon: OdsTopAppBarNavigationIcon?` | `null` | Icon displayed at the start of the top app bar
`actions: List<OdsTopAppBarActionButton>` | `emptyList()` | Actions displayed at the end of the top app bar. The default layout here is a `Row`, so icons inside will be placed horizontally.
`overflowMenuActions: List<OdsTopAppBarOverflowMenuActionItem>` | `emptyList()` | Actions displayed in the overflow menu
`scrollBehavior: TopAppBarScrollBehavior` | `null` | `TopAppBarScrollBehavior` attached to the top app bar
{:.table}

## Component specific tokens

_Soon available_
