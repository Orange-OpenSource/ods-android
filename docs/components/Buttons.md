---
layout: detail
title: Buttons
description: Buttons allow users to take actions, and make choices, with a single tap.
---

<br>**On this page**

* [Specifications references](#specifications-references)
* [Accessibility](#accessibility)
* [Variants](#variants)
    * [Contained button](#contained-button)
        * [Jetpack Compose](#jetpack-compose)
            * [OdsButton API](#odsbutton-api)
    * [Text button](#text-button)
        * [Jetpack Compose](#jetpack-compose-1)
            * [OdsTextButton API](#odstextbutton-api)
    * [Outlined button](#outlined-button)
        * [Jetpack Compose](#jetpack-compose-2)
            * [OdsOutlinedButton API](#odsoutlinedbutton-api)
    * [Text toggle buttons group](#text-toggle-buttons-group)
        * [Jetpack Compose](#jetpack-compose-3)
            * [OdsTextToggleButtonsRow API](#odstexttogglebuttonsrow-api)
    * [Icon button](#icon-button)
        * [Jetpack Compose](#jetpack-compose-4)
            * [OdsIconButton API](#odsiconbutton-api)
    * [Icon toggle button](#icon-toggle-button)
        * [Jetpack Compose](#jetpack-compose-5)
            * [OdsIconToggleButton API](#odsicontogglebutton-api)
    * [Icon toggle buttons group](#icon-toggle-buttons-group)
        * [Jetpack Compose](#jetpack-compose-6)
            * [OdsIconToggleButtonsRow API](#odsicontogglebuttonsrow-api)

---

## Specifications references

- [Design System Manager - Buttons](https://system.design.orange.com/0c1af118d/p/06a393-buttons/b/530521)
- [Material Design - Buttons](https://material.io/components/buttons/)

## Accessibility

Please follow [accessibility criteria for development](https://a11y-guidelines.orange.com/en/mobile/android/development/).

ODS buttons support accessibility criteria and are readable by most screen readers, such as TalkBack.

Content descriptions for icons are unnecessary in the case of buttons containing text. For other buttons types, such as `OdsIconButton`, icons content descriptions are mandatory in the APIs.

## Variants

### Contained button

Contained buttons are high-emphasis, distinguished by their use of elevation and fill. They contain
actions that are primary to your app.

![ContainedButton](images/button_contained_light.png) ![ContainedButton dark](images/button_contained_dark.png)

Functional positive:

![ContainedButton positive light](images/button_contained_positive_light.png) ![ContainedButton positive dark](images/button_contained_positive_dark.png)

Functional negative:

![ContainedButton negative light](images/button_contained_negative_light.png) ![ContainedButton negative dark](images/button_contained_negative_dark.png)

#### Jetpack Compose

Use the `OdsButton` composable:

```kotlin
OdsButton(
    text = "Contained button",
    onClick = { doSomething() },
    enabled = true,
    icon = OdsButtonIconBuilder(painterResource(R.drawable.ic_coffee)) // Line can be removed if you don't need any icon
)
```

To display a primary button or a functional green/red button, you need to pass an `OdsButtonStyle`
through the `style` parameter:

```kotlin
OdsButton(
    text = "Positive button",
    onClick = { doSomething() },
    enabled = true,
    icon = OdsButtonIconBuilder(painterResource(R.drawable.ic_coffee)), // Line can be removed if you don't need any icon
    style = OdsButtonStyle.FunctionalPositive
)
```

##### OdsButton API

Parameter | Default&nbsp;value | Description
-- | -- | --
`text: String` | | Text displayed into the button
`onClick: () -> Unit` | | Callback invoked when the button is clicked
`modifier: Modifier` | `Modifier` | `Modifier` applied to the button
`icon: OdsButtonIconBuilder?` | `null` | Icon displayed in the button before the text
`enabled: Boolean` | `true` | Controls the enabled state of the button. When `false`, this button will not be clickable.
`style: OdsButtonStyle` | `OdsButtonStyle.Default` | Style applied to the button. Set it to `OdsButtonStyle.Primary` for an highlighted button style or use `OdsButtonStyle.FunctionalPositive`/ `OdsButtonStyle.FunctionalNegative` for a functional green/red button style.
`displaySurface: OdsDisplaySurface` | `OdsDisplaySurface.Default` | `OdsDisplaySurface` applied to the button. It allows to force the button display on light or dark surface. By default, the appearance applied is based on the system night mode value.
{:.table}

### Text button

Text buttons are typically used for less-pronounced actions, including those located in dialogs and
cards. In cards, text buttons help maintain an emphasis on card content.

![TextButton](images/button_text_light.png) ![TextButton dark](images/button_text_dark.png)

#### Jetpack Compose

Use the `OdsTextButton` composable:

```kotlin
OdsTextButton(
    text = "Text button",
    onClick = { doSomething() },
    enabled = true,
    icon = OdsButtonIconBuilder(painterResource(R.drawable.ic_coffee)), // Line can be removed if you don't need any icon
    style = OdsTextButtonStyle.Primary
)
```

##### OdsTextButton API

Parameter | Default&nbsp;value | Description
-- | -- | --
`text: String` | | Text displayed into the button
`onClick: () -> Unit` | | Callback invoked on button click
`modifier: Modifier` | `Modifier` | `Modifier` applied to the button
`icon: OdsButtonIconBuilder?` | `null` | Icon displayed in the button before the text
`enabled: Boolean` | `true` | Controls the enabled state of the button. When `false`, this button will not be clickable.
`style: OdsTextButtonStyle` | `OdsTextButtonStyle.Default` | Style applied to the button. By default `onSurface` color is used for text color. Use `OdsTextButtonStyle.Primary` for an highlighted text color.
`displaySurface: OdsDisplaySurface` | `OdsDisplaySurface.Default` | `OdsDisplaySurface` applied to the button. It allows to force the button display on light or dark surface. By default, the appearance applied is based on the system night mode value.
{:.table}

### Outlined button

Outlined buttons are medium-emphasis buttons. They contain actions that are important, but aren’t
the primary action in an app.

![ButtonOutlined](images/button_outlined_light.png) ![ButtonOutlined dark](images/button_outlined_dark.png)

#### Jetpack Compose

Use the `OdsOutlinedButton` composable:

```kotlin
OdsOutlinedButton(
    text = "Outlined button",
    onClick = {},
    enabled = true,
    icon = OdsButtonIconBuilder(painterResource(R.drawable.ic_coffee)) // Line can be removed if you don't need any icon
)
```

##### OdsOutlinedButton API

Parameter | Default&nbsp;value | Description
-- | -- | --
`text: String` | | Text displayed into the button
`onClick: () -> Unit` | | Callback invoked on button click
`modifier: Modifier` | `Modifier` | `Modifier` applied to the button
`icon: OdsButtonIconBuilder?` | `null` | Icon displayed in the button before the text
`enabled: Boolean` | `true` | Controls the enabled state of the button. When `false`, the button is not clickable.
`displaySurface: OdsDisplaySurface` | `OdsDisplaySurface.Default` | `OdsDisplaySurface` applied to the button. It allows to force the button display on light or dark surface. By default, the appearance applied is based on the system night mode value.
{:.table}

### Text toggle buttons group

A group of text toggle buttons. Only one option in a group of toggle buttons can be selected and active at a time.
Selecting one option deselects any other.

![Button text toggle group light](images/button_text_toggle_group_light.png) ![Button text toggle group dark](images/button_text_toggle_group_dark.png)

#### Jetpack Compose

Use the `OdsTextToggleButtonsRow` composable:

```kotlin
OdsTextToggleButtonsRow(
    textToggleButtons = listOf(
        OdsTextToggleButtonsRowItemBuilder("XML", true),
        OdsTextToggleButtonsRowItemBuilder("COMPOSE", true),
    ),
    selectedIndex = 0,
    onSelectedIndexChange = {
        doSomething() // Do something like changing selectedIndex to refresh composable with new selection
    },
    sameItemsWeight = false
)
```

##### OdsTextToggleButtonsRow API

Parameter | Default&nbsp;value | Description
-- | -- | --
`textToggleButtons: List<OdsTextToggleButtonsRowItemBuilder>` | | Items displayed into the toggle group
`selectedIndex: Int` | | `textToggleButtons` list index of the selected button
`onSelectedIndexChange: (Int) -> Unit` | | Callback invoked on selection change
`modifier: Modifier` | `Modifier` | `Modifier` applied to the toggle buttons row
`sameItemsWeight: Boolean` | `false` | Controls the place occupied by each item. When `true`, same weight of importance will be applied to each item, they will occupy the same width.
`displaySurface: OdsDisplaySurface` | `OdsDisplaySurface.Default` | `OdsDisplaySurface` applied to the button. It allows to force the button display on light or dark surface. By default, the appearance applied is based on the system night mode value.
{:.table}

### Icon button

An icon button is a clickable icon, used to represent actions. This component is typically used
inside an App Bar for the navigation icon / actions.

![OdsIconButton](images/button_icon_light.png) ![OdsIconButton dark](images/button_icon_dark.png)

#### Jetpack Compose

Use the `OdsIconButton` composable:

```kotlin
OdsIconButton(
    icon = OdsIconButtonIconBuilder(
        painterResource(id = R.drawable.ic_ui_light_mode),
        stringResource(id = R.string.theme_changer_icon_content_description_light)
    ),
    onClick = { doSomething() },
)
```

##### OdsIconButton API

Parameter | Default&nbsp;value | Description
-- | -- | --
`icon: OdsIconButtonIconBuilder` | | Icon to be drawn into the button
`onClick: () -> Unit` | | Callback to be invoked when the button is clicked
`modifier: Modifier` | `Modifier` | `Modifier` to be applied to the button
`enabled: Boolean` | `true` | Controls the enabled state of the button. When `false`, this button will not be clickable.
`displaySurface: OdsDisplaySurface` | `OdsDisplaySurface.Default` | `OdsDisplaySurface` to be applied to the button. It allows to force the button display on light or dark surface. By default, the appearance applied is based on the system night mode value.
{:.table}

### Icon toggle button

An icon button with two states, for icons that can be toggled 'on' and 'off', such as a bookmark
icon, or a navigation icon that opens a drawer.

![Button icon toggle light](images/button_icon_toggle_light.png) ![Button icon toggle dark](images/button_icon_toggle_dark.png)

#### Jetpack Compose

Use the `OdsIconToggleButton` composable:

```kotlin
OdsIconToggleButton(
    checked = false,
    onCheckedChange = { doSomething() },
    uncheckedIcon = OdsIconButtonIconBuilder(
        painterResource(R.drawable.ic_heart_outlined),
        "Add to favorites"
    ),
    checkedIcon = OdsIconButtonIconBuilder(
        painterResource(R.drawable.ic_heart),
        "Remove from favorites"
    )
)
```

##### OdsIconToggleButton API

Parameter | Default&nbsp;value | Description
-- | -- | --
`checked: Boolean` | | Controls the checked state of the button
`onCheckedChange: (Boolean) -> Unit` | | Callback invoked when the button is checked
`uncheckedIcon: OdsIconButtonIconBuilder` | | Icon displayed when the button is unchecked
`checkedIcon: OdsIconButtonIconBuilder` | | Icon displayed when the button is checked
`modifier: Modifier` | `Modifier` | `Modifier` applied to the button
`enabled: Boolean` | `true` | Controls the enabled state of the button. When `false`, this button will not be clickable.
`displaySurface: OdsDisplaySurface` | `OdsDisplaySurface.Default` | `OdsDisplaySurface` applied to the button. It allows to force the button display on light or dark surface. By default, the appearance applied is based on the system night mode value.
{:.table}

### Icon toggle buttons group

A group of toggle buttons. Only one option in a group of toggle buttons can be selected and active
at a time.
Selecting one option deselects any other.

![Button icon toggle group light](images/button_icon_toggle_group_light.png) ![Button icon toggle group dark](images/button_icon_toggle_group_dark.png)

#### Jetpack Compose

Use the `OdsIconToggleButtonsRow` composable:

```kotlin
OdsIconToggleButtonsRow(
    icons = listOf(
        OdsIconToggleButtonsRowIconBuilder(
            painterResource(id = R.drawable.ic_restaurant),
            "Restaurant"
        ),
        OdsIconToggleButtonsRowIconBuilder(
            painterResource(id = R.drawable.ic_cooking_pot),
            "Cooking pot"
        ),
        OdsIconToggleButtonsRowIconBuilder(
            painterResource(id = R.drawable.ic_coffee),
            "Coffee",
            enabled = false
        )
    ),
    selectedIndex = 0,
    onSelectedIndexChange = {
        doSomething() // Do something like changing selectedIndex to refresh composable with new selection                        
    },
    displaySurface = displaySurface
)
```

##### OdsIconToggleButtonsRow API

Parameter | Default&nbsp;value | Description
-- | -- | --
`icons: List<OdsIconToggleButtonsRowIconBuilder>` | | Icons to be displayed into the toggle group
`selectedIndex: Int` | | `icons` list index of the selected button
`onSelectedIndexChange: (Int) -> Unit` | | Callback invoked on selection change
`modifier: Modifier` | `Modifier` | `Modifier` applied to the toggle buttons group
`displaySurface: OdsDisplaySurface` | `OdsDisplaySurface.Default` | `OdsDisplaySurface` applied to the button. It allows to force the button display on light or dark surface. By default, the appearance applied is based on the system night mode value.
{:.table}
