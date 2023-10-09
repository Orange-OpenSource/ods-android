---
layout: detail
title: Chips
description: Chips are compact elements that represent an input, attribute, or action.
---

---

**Page summary**

* [Specifications references](#specifications-references)
* [Accessibility](#accessibility)
* [Variants](#variants)
    * [Input chip](#input-chip)
    * [Choice chip](#choice-chip)
    * [Filter chip](#filter-chip)
    * [Action chip](#action-chip)
* [OdsChip API](#odschip-api)
* [Related components](#related-components)
    * [Choice chips flow row](#choice-chips-flow-row)
* [Component specific tokens](#component-specific-tokens)

---

## Specifications references

- [Design System Manager](https://system.design.orange.com/0c1af118d/p/81aa91-chips/b/13c40e)
- [Material Design](https://material.io/components/chips)

## Accessibility

Please follow [accessibility criteria for development](https://a11y-guidelines.orange.com/en/mobile/android/development/)

Chips support content labeling for accessibility and are readable by most screen readers, such as
TalkBack. Text rendered in chips is automatically provided to accessibility services. Additional
content labels are usually unnecessary.

## Variants

### Input chip

Input chips (referred to as **entry** chips in Android) represent a complex piece of information in
compact form, such as an entity (person, place, or thing) or text. They enable user input and verify
that input by converting text into chips.

![Light input chip](images/chips_input_light.png) ![Dark input chip](images/chips_input_dark.png)

![Light outlined input chip](images/chips_input_outlined_light.png) ![Dark outlined input chip](images/chips_input_outlined_dark.png)

#### Jetpack Compose implementation

Use the `OdsChip` composable.
Note that the chip style is outlined or filled according to your OdsTheme component configuration,
outlined by default.

```kotlin
OdsChip(
    text = "chip text",
    onClick = {
        doSomething()
    },
    leadingIcon = null,
    leadingAvatar = OdsChipLeadingAvatar(
        painterResource(id = R.drawable.avatar),
        "Avatar"
    ), // Set it to `null` for no avatar or provide a `leadingIcon`
    enabled = true,
    onCancel = {
        doSomething()
    }
)
```

Parameter | Default value | Description
-- | -- | --
`text: String` | | Text to be displayed into the chip
`onClick: () -> Unit` | | Callback called on chip click
`modifier: Modifier` | `Modifier` | `Modifier` to be applied to the chip
`enabled: Boolean` | `true` | Controls the enabled state of the chip. When `false`, this chip will not respond to user input.
`selected: Boolean` | `false` | Controls the selected state of the chip. When `true`, the chip is highlighted (useful for choice chips).
`leadingIcon: OdsChipLeadingIcon?` | `null` | Icon to be displayed at the start of the chip, preceding the text
`leadingAvatar: OdsChipLeadingAvatar?` | `null` | Avatar to be displayed in a circle shape at the start of the chip, preceding the content text
`onCancel: (() -> Unit)?` | `null` | Callback called on chip cancel cross click. Pass `null` for no cancel cross.
{:.table}

### Choice chip

Choice chips allow selection of a single chip from a set of options.

Choice chips clearly delineate and display options in a compact area. They are a good alternative to
toggle buttons, radio buttons, and single select menus.

**Note: To display a set of choice chips please see [Choice chips flow row](#choice-chips-flow-row)
**

![Light choice chips](images/chips_choice_light.png) ![Dark choice chips](images/chips_choice_dark.png)

![Light outlined choice chips](images/chips_choice_outlined_light.png) ![Dark outlined choice chips](images/chips_choice_outlined_dark.png)

#### Jetpack Compose implementation

Use the `OdsChip` composable.
Note that the chip style is outlined or filled according to your OdsTheme component configuration,
outlined by default.

```kotlin
OdsChip(
    text = "chip text",
    onClick = {
        doSomething()
    },
    enabled = true,
)
```

Use the same API as the [Input chip](#input-chip).

### Filter chip

Filter chips use tags or descriptive words to filter content.

Filter chips clearly delineate and display options in a compact area. They are a good alternative to
toggle buttons or checkboxes.

![Light filter chips](images/chips_filter_light.png) ![Dark filter chips](images/chips_filter_dark.png)

![Light filter chips with avatar](images/chips_filter_avatar_light.png) ![Dark filter chips with avatar](images/chips_filter_avatar_dark.png)

#### Jetpack Compose implementation

Use the `OdsFilterChip` composable.
Note that the chip style is outlined or filled according to your OdsTheme component configuration,
outlined by default.

```kotlin
OdsFilterChip(
    text = "chip text",
    onClick = {
        doSomething()
    },
    leadingAvatar = OdsChipLeadingAvatar(
        painterResource(id = R.drawable.avatar),
        ""
    ), // Set it to `null` for no avatar
    selected = false,
    enabled = true,
)
```

Parameter | Default value | Description
-- | -- | --
`text: String` | | Text to be displayed into the chip
`onClick: () -> Unit` | | Callback called on chip click
`modifier: Modifier` | `Modifier` | `Modifier` to be applied to the chip
`enabled: Boolean` | `true` | Controls the enabled state of the chip. When `false`, this chip will not respond to user input. It also appears visually disabled and is disabled to accessibility services.
`selected: Boolean` | `false` | Controls the selected state of the chip. When `true`, the chip is highlighted.
`leadingAvatar: OdsChipLeadingAvatar?` | `null` | Avatar to be displayed in a circle shape at the start of the chip, preceding the content text
{:.table}

### Action chip

Action chips offer actions related to primary content. They should appear dynamically and
contextually in a UI.

An alternative to action chips are buttons, which should appear persistently and consistently.

![Light action chip](images/chips_action_light.png) ![Dark action chip](images/chips_action_dark.png)

![Light outlined action chip](images/chips_action_outlined_light.png) ![Dark outlined action chip](images/chips_action_outlined_dark.png)

#### Jetpack Compose implementation

Use the `OdsChip` composable.
Note that the chip style is outlined or filled according to your OdsTheme component configuration,
outlined by default.

```kotlin
OdsChip(
    text = "chip text",
    onClick = {
        doSomething()
    },
    leadingIcon = OdsChipLeadingIcon(
        painterResource(id = R.drawable.ic_heart),
        "Heart"
    ), // set it to `null` for no icon
    enabled = true,
)
```

Use the same API as the [Input chip](#input-chip).

## Related components

The ODS library provides some chips related components to facilitate the implementation of chips groups.

### Choice chips flow row

This is a full width `FlowRow` containing selectable chips. It works like radio buttons, only one chip of the set can be selected.

![Light choice chips flow row](images/chips_choice_flow_row_light.png)

![Dark choice chips flow row](images/chips_choice_flow_row_dark.png)

Use `OdsChoiceChipsFlowRow` composable.
Note that the chips style is outlined or filled according to your OdsTheme component configuration,
outlined by default.

```kotlin
OdsChoiceChipsFlowRow(
    value = chipValue,
    onValueChange = { value -> chipValue = value },
    modifier = Modifier.padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.spacing_m)),
    chips = listOf(
        OdsChoiceChip(text = "Choice chip 1", value = 1),
        OdsChoiceChip(text = "Choice chip 2", value = 2)
    )
)
```

Parameter | Default value | Description
-- | -- | --
`value: String` | | Initial value of the choice chips flow row
`onValueChange: (value: T) -> Unit` | | Callback invoked when the value changes. The new value is provided as parameter.
`modifier: Modifier` | `Modifier` | `Modifier` applied to the chips flow row
`chips: List<OdsChoiceChip<T>>` | | Chips displayed into the flow row
{:.table}

## Component specific tokens

_Soon available_
