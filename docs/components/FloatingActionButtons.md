---
layout: detail
title: Floating action buttons
description: A floating action button (FAB) represents the primary action of a screen.
---

<br>**On this page**

* [Specifications references](#specifications-references)
* [Accessibility](#accessibility)
* [Variants](#variants)
    * [Regular FAB](#regular-fab)
        * [Jetpack Compose](#jetpack-compose)
            * [OdsFloatingActionButton API](#odsfloatingactionbutton-api)
    * [Mini FAB](#mini-fab)
        * [Jetpack Compose](#jetpack-compose-1)
    * [Extended FAB](#extended-fab)
        * [Jetpack Compose](#jetpack-compose-2)
            * [OdsExtendedFloatingActionButton API](#odsextendedfloatingactionbutton-api)

---

## Specifications references

- [Design System Manager - Floating Action Button](https://system.design.orange.com/0c1af118d/p/577022-buttons-fab/b/101b2a)
- [Material Design - Buttons: floating action button](https://material.io/components/buttons-floating-action-button/)

## Accessibility

Please follow [accessibility criteria for development](https://a11y-guidelines.orange.com/en/mobile/android/development/).

The `OdsFloatingActionButtonIconBuilder` used in Floating Action Buttons APIs force the developers to set a content description to the FABs so that
screen readers like TalkBack are able to announce their purpose or action.

Text rendered in an extended FAB is automatically provided to accessibility services, so additional content labels are usually unnecessary.
In this context you can set an empty `contentDescription`.

## Variants

### Regular FAB

Regular FABs are FABs that are not expanded and are a regular size.

![FAB light](images/fab_light.png)  ![FAB dark](images/fab_dark.png)

#### Jetpack Compose

To display a regular Floating Action Button in your composable screen, use `OdsFloatingActionButton`:

```kotlin
OdsFloatingActionButton(
    onClick = { doSomething() },
    mini = false,
    icon = OdsFloatingActionButtonIcon(
        painterResource(id = R.drawable.ic_plus),
        stringResource(id = R.string.add)
    ),
    modifier = modifier
)
```

##### OdsFloatingActionButton API

Parameter | Default&nbsp;value | Description
-- | -- | --
`icon: OdsFloatingActionButtonIconBuilder` | | Icon used into the FAB
`onClick: () -> Unit` | | Callback invoked on FAB click
`modifier: Modifier` | `Modifier` | `Modifier` applied to the FAB
`mini: Boolean` | `false` | Controls the size of the FAB. If `true`, the size of the FAB will be 40dp, otherwise the default size will be used.
{:.table}

### Mini FAB

A mini FAB should be used on smaller screens.

Mini FABs can also be used to create visual continuity with other screen elements.

![FAB mini light](images/fab_mini_light.png)  ![FAB mini dark](images/fab_mini_dark.png)

#### Jetpack Compose

To display a mini FAB in your composable screen use `OdsFloatingActionButton`

```kotlin
OdsFloatingActionButton(
    onClick = { doSomething() },
    mini = true,
    icon = OdsFloatingActionButtonIconBuilder(
        painterResource(id = R.drawable.ic_plus),
        stringResource(id = R.string.add)
    ),
    modifier = modifier
)
```

Use [OdsFloatingActionButton API](#odsfloatingactionbutton-api).

### Extended FAB

The extended FAB is wider, and it includes a text label.

![FAB extended light](images/fab_extended_light.png)  ![FAB extended dark](images/fab_extended_dark.png)

![FAB extended full width light](images/fab_extended_full_width_light.png)  ![FAB extended full width dark](images/fab_extended_full_width_dark.png)

#### Jetpack Compose

To display an extended FAB, use `OdsExtendedFloatingActionButton`:

```kotlin
OdsExtendedFloatingActionButton(
    onClick = { doSomething() },
    text = stringResource(id = R.string.add),
    icon = OdsFloatingActionButtonIconBuilder(painterResource(id = R.drawable.ic_plus), ""),
    modifier = modifier
)
```

##### OdsExtendedFloatingActionButton API

Parameter | Default&nbsp;value | Description
-- | -- | --
`icon: OdsFloatingActionButtonIconBuilder` | | Icon used into the FAB
`onClick: () -> Unit` | | Callback invoked on FAB click
`text: String` | | Text displayed into the FAB
`modifier: Modifier` | `Modifier` | `Modifier` applied to the FAB
{:.table}
