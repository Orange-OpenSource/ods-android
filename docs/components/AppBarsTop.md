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
        * [Jetpack Compose](#jetpack-compose)
            * [OdsTopAppBar API](#odstopappbar-api)
    * [Large top app bar](#large-top-app-bar)
        * [Jetpack Compose](#jetpack-compose-1)
            * [OdsLargeTopAppBar API](#odslargetopappbar-api)
    * [Search top app bar](#search-top-app-bar)
        * [Jetpack Compose](#jetpack-compose-2)
            * [OdsSearchTopAppBar API](#odssearchtopappbar-api)

---

## Specifications references

- [Design System Manager - App bars](https://system.design.orange.com/0c1af118d/p/23e0e6-app-bars/b/620966)
- [Material Design - App bars: top](https://material.io/components/app-bars-top/)

## Accessibility

Please follow [accessibility criteria for development](https://a11y-guidelines.orange.com/en/mobile/android/development/).

`OdsTopAppBar` provides accessibility support for the navigation icon,
action items, overflow menu and more for informing the user as to what each
action performs.

## Variants

### Regular top app bar

#### Jetpack Compose

Add `OdsTopAppBar` composable to your Scaffold `topBar`.
Here is an example of use:

```kotlin
OdsTopAppBar(
    title = "Title",
    navigationIcon = OdsTopAppBar.NavigationIcon(
        painter = painterResource(id = R.drawable.ic_back),
        contentDescription = "content description",
        onClick = { doSomething() }
    ),
    actions = listOf(
        OdsTopAppBar.ActionButton(
            painter = painterResource(id = R.drawable.ic_share),
            contentDescription = "content description",
            onClick = { doSomething() }
        ),
        // ...
    ),
    overflowMenuItems = listOf(
        OdsDropdownMenu.Item(
            text = "Text",
            onClick = { doSomething() }
        ),
        // ...
    )
)
```

Note: By default, the `OdsTopAppBar` is elevated but you can set `elevated` parameter to `false` if you don't want any shadow below it (for example if you want to display tabs below).

##### OdsTopAppBar API

{:.table}

| Parameter                                       | Default&nbsp;value | Description                                                                                                                               |
|-------------------------------------------------|--------------------|-------------------------------------------------------------------------------------------------------------------------------------------|
| `title: String`                                 |                    | Title to be displayed in the center of the top app bar                                                                                    |
| `modifier: Modifier`                            | `Modifier`         | `Modifier` to be applied to the top app bar                                                                                               |
| `navigationIcon: OdsTopAppBar.NavigationIcon?`  | `null`             | Icon to be displayed at the start of the top app bar                                                                                      |
| `actions: List<OdsTopAppBar.ActionButton>`      | `emptyList()`      | Actions to be displayed at the end of the top app bar. The default layout here is a `Row`, so icons inside will be placed horizontally.   |
| `overflowMenuItems: List<OdsDropdownMenu.Item>` | `emptyList()`      | List of items displayed in the overflow menu. The top app bar uses `OdsDropdownMenu` to display its overflow menu.                        |
| `elevated: Boolean`                             | `true`             | Controls the elevation of the top app bar: `true` to set an elevation to the top app bar (a shadow is displayed below), `false` otherwise |

### Large top app bar

#### Jetpack Compose

First, you have to add this line in your application `build.gradle.kts` file cause this component relies on Compose Material 3 implementation:

```kotlin
implementation("androidx.compose.material3:material3:<version number>")
```

Then you can add `OdsLargeTopAppBar` composable to your Scaffold `topBar`:

```kotlin
OdsLargeTopAppBar(
    title = "Title",
    navigationIcon = OdsTopAppBar.NavigationIcon(
        painter = painterResource(id = R.drawable.ic_back),
        contentDescription = "content description",
        onClick = { doSomething() }
    ),
    actions = listOf(
        OdsTopAppBar.ActionButton(
            painter = painterResource(id = R.drawable.ic_share),
            contentDescription = "content description",
            onClick = { doSomething() }
        ),
        // ...
    ),
    overflowMenuItems = listOf(
        OdsDropdownMenu.Item(
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

{:.table}

| Parameter                                       | Default&nbsp;value | Description                                                                                                                       |
|-------------------------------------------------|--------------------|-----------------------------------------------------------------------------------------------------------------------------------|
| `title: String`                                 |                    | Title displayed in the center of the top app bar                                                                                  |
| `modifier: Modifier`                            | `Modifier`         | `Modifier` applied to the top app bar                                                                                             |
| `navigationIcon: OdsTopAppBar.NavigationIcon?`  | `null`             | Icon displayed at the start of the top app bar                                                                                    |
| `actions: List<OdsTopAppBar.ActionButton>`      | `emptyList()`      | Actions displayed at the end of the top app bar. The default layout here is a `Row`, so icons inside will be placed horizontally. |
| `overflowMenuItems: List<OdsDropdownMenu.Item>` | `emptyList()`      | List of items displayed in the overflow menu. The top app bar uses `OdsDropdownMenu` to display its overflow menu.                |
| `scrollBehavior: TopAppBarScrollBehavior?`      | `null`             | `TopAppBarScrollBehavior` attached to the top app bar                                                                             |

### Search top app bar

#### Jetpack Compose

Add `OdsSearchTopAppBar` composable to your Scaffold `topBar`.
Here is an example of use:

```kotlin
OdsSearchTopAppBar(
    placeholder = "Enter text to search",
    value = TextFieldValue(),
    onValueChange = { value -> doSomethingWith(value) },
    navigationIcon = OdsTopAppBar.NavigationIcon(
        painter = painterResource(id = R.drawable.ic_back),
        contentDescription = "content description",
        onClick = { doSomething() }
    )
)
```

##### OdsSearchTopAppBar API

{:.table}

| Parameter                                        | Default&nbsp;value | Description                                                                                                                               |
|--------------------------------------------------|--------------------|-------------------------------------------------------------------------------------------------------------------------------------------|
| <b>`placeholder: String`</b>                     |                    | Text placeholder displayed in the search text field when search value is empty                                                            |
| <b>`value: TextFieldValue`</b>                   |                    | Value of the search text field                                                                                                            |
| <b>`onValueChange: (TextFieldValue) -> Unit`</b> |                    | Callback invoked when the search value changes. The new value is available in parameter.                                                  |
| `modifier: Modifier`                             | `Modifier`         | `Modifier` to be applied to the search top app bar                                                                                        |
| `navigationIcon: OdsTopAppBar.NavigationIcon?`   | `null`             | Icon to be displayed at the start of the top app bar before the text field                                                                |
| `elevated: Boolean`                              | `true`             | Controls the elevation of the top app bar: `true` to set an elevation to the top app bar (a shadow is displayed below), `false` otherwise |
