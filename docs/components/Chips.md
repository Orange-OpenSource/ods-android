---
layout: detail
title: Chips
description: Chips are compact elements that represent an input, attribute, or action.
---

<br>**On this page**

* Table of contents
{:toc}

---

## Specifications references

- [Design System Manager](https://system.design.orange.com/0c1af118d/p/81aa91-chips/b/13c40e)
- [Material Design](https://material.io/components/chips)

## Accessibility

Please follow [accessibility criteria for development](https://a11y-guidelines.orange.com/en/mobile/android/development/).

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

#### Jetpack Compose

Use the `OdsChip` composable.
Note that the chip style is outlined or filled according to your OdsTheme component configuration,
outlined by default.

```kotlin
OdsChip(
    text = "chip text",
    onClick = {
        doSomething()
    },
    leading = OdsChip.LeadingAvatar(
        painterResource(id = R.drawable.avatar),
        "Avatar"
    ),
    enabled = true,
    onCancel = {
        doSomething()
    }
)
```

##### OdsChip API

| Parameter                    | Default&nbsp;value | Description                                                                                              |
|------------------------------|--------------------|----------------------------------------------------------------------------------------------------------|
| <b>`text: String`</b>        |                    | Text to be displayed into the chip                                                                       |
| <b>`onClick: () -> Unit`</b> |                    | Callback called on chip click                                                                            |
| `modifier: Modifier`         | `Modifier`         | `Modifier` to be applied to the chip                                                                     |
| `enabled: Boolean`           | `true`             | Controls the enabled state of the chip. When `false`, this chip will not respond to user input.          |
| `selected: Boolean`          | `false`            | Controls the selected state of the chip. When `true`, the chip is highlighted (useful for choice chips). |
| `leading: OdsChip.Leading?`  | `null`             | The leading content to be displayed at the start of the chip, preceding the text                         |
| `onCancel: (() -> Unit)?`    | `null`             | Callback called on chip cancel cross click. Pass `null` for no cancel cross.                             |

### Choice chip

Choice chips allow selection of a single chip from a set of options.

Choice chips clearly delineate and display options in a compact area. They are a good alternative to
toggle buttons, radio buttons, and single select menus.

**Note: To display a set of choice chips please see [Choice chips flow row](#choice-chips-flow-row)
**

![Light choice chips](images/chips_choice_light.png) ![Dark choice chips](images/chips_choice_dark.png)

![Light outlined choice chips](images/chips_choice_outlined_light.png) ![Dark outlined choice chips](images/chips_choice_outlined_dark.png)

#### Jetpack Compose

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

Use the [OdsChip API](#odschip-api).

### Filter chip

Filter chips use tags or descriptive words to filter content.

Filter chips clearly delineate and display options in a compact area. They are a good alternative to
toggle buttons or checkboxes.

![Light filter chips](images/chips_filter_light.png) ![Dark filter chips](images/chips_filter_dark.png)

![Light filter chips with avatar](images/chips_filter_avatar_light.png) ![Dark filter chips with avatar](images/chips_filter_avatar_dark.png)

#### Jetpack Compose

Use the `OdsFilterChip` composable.
Note that the chip style is outlined or filled according to your OdsTheme component configuration,
outlined by default.

```kotlin
OdsFilterChip(
    text = "chip text",
    onClick = {
        doSomething()
    },
    leadingAvatar = OdsChip.LeadingAvatar(
        painterResource(id = R.drawable.avatar),
        ""
    ), // Set it to `null` for no avatar
    selected = false,
    enabled = true,
)
```

##### OdsFilterChip API

| Parameter                               | Default&nbsp;value | Description                                                                                                                                                                  |
|-----------------------------------------|--------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| <b>`text: String`</b>                   |                    | Text to be displayed into the chip                                                                                                                                           |
| <b>`onClick: () -> Unit`</b>            |                    | Callback called on chip click                                                                                                                                                |
| `modifier: Modifier`                    | `Modifier`         | `Modifier` to be applied to the chip                                                                                                                                         |
| `enabled: Boolean`                      | `true`             | Controls the enabled state of the chip. When `false`, this chip will not respond to user input. It also appears visually disabled and is disabled to accessibility services. |
| `selected: Boolean`                     | `false`            | Controls the selected state of the chip. When `true`, the chip is highlighted.                                                                                               |
| `leadingAvatar: OdsChip.LeadingAvatar?` | `null`             | Avatar to be displayed in a circle shape at the start of the chip, preceding the content text                                                                                |

### Action chip

Action chips offer actions related to primary content. They should appear dynamically and
contextually in a UI.

An alternative to action chips are buttons, which should appear persistently and consistently.

![Light action chip](images/chips_action_light.png) ![Dark action chip](images/chips_action_dark.png)

![Light outlined action chip](images/chips_action_outlined_light.png) ![Dark outlined action chip](images/chips_action_outlined_dark.png)

#### Jetpack Compose

Use the `OdsChip` composable.
Note that the chip style is outlined or filled according to your OdsTheme component configuration,
outlined by default.

```kotlin
OdsChip(
    text = "chip text",
    onClick = {
        doSomething()
    },
    leading = OdsChip.LeadingIcon(
        painterResource(id = R.drawable.ic_heart),
        "Heart"
    ), // set it to `null` for no icon
    enabled = true,
)
```

Use the [OdsChip API](#odschip-api).

## Extras

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
    selectedChoiceChipIndex = selectedChoiceChipIndex,
    choiceChips = listOf(
        OdsChoiceChipsFlowRow.ChoiceChip("Choice chip 1", { selectedChoiceChipIndex = 0 }),
        OdsChoiceChipsFlowRow.ChoiceChip("Choice chip 2", { selectedChoiceChipIndex = 1 })
    ),
    modifier = Modifier.padding(horizontal = OdsTheme.spacings.medium)
)
```

#### OdsChoiceChipsFlowRow API

| Parameter                                                    | Default&nbsp;value | Description                                                                       |
|--------------------------------------------------------------|--------------------|-----------------------------------------------------------------------------------|
| <b>`selectedChoiceChipIndex: Int`</b>                        |                    | The index of the currently selected chip.                                         |
| <b>`choiceChips: List<OdsChoiceChipsFlowRow.ChoiceChip>`</b> |                    | The list of `OdsChoiceChipsFlowRow.ChoiceChip` displayed into the chips flow row. |
| `modifier: Modifier`                                         | `Modifier`         | `Modifier` applied to the chips flow row.                                         |
