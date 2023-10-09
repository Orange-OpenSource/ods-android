---
layout: detail
title: Floating action buttons
description: A floating action button (FAB) represents the primary action of a screen.
---

---

**Page Summary**

* [Specifications references](#specifications-references)
* [Accessibility](#accessibility)
* [Variants](#variants)
    * [Regular FAB](#regular-fab)
    * [Mini FAB](#mini-fab)
    * [Extended FAB](#extended-fab)
* [Component specific tokens](#component-specific-tokens)

---

## Specifications references

- Design System Manager - Floating Action Button (soon available)
- [Material Design - Buttons: floating action button](https://material.io/components/buttons-floating-action-button/)

## Accessibility

Please follow [accessibility criteria for development](https://a11y-guidelines.orange.com/en/mobile/android/development/)

You should set a content description on a FAB via the
`android:contentDescription` attribute or `setContentDescription` method so that
screen readers like TalkBack are able to announce their purpose or action. Text
rendered in Extended FABs is automatically provided to accessibility services,
so additional content labels are usually unnecessary.

## Variants

### Regular FAB

Regular FABs are FABs that are not expanded and are a regular size.

![FAB light](images/fab_light.png)  ![FAB dark](images/fab_dark.png)

#### Jetpack Compose implementation

To display a regular Floating Action Button in your composable screen, use `OdsFloatingActionButton`:

```kotlin
OdsFloatingActionButton(
    onClick = {
        // Do something
    },
    mini = false, // Set to `true` for a Mini FAB variant
    icon = OdsFloatingActionButtonIcon(
        painterResource(id = R.drawable.ic_plus),
        stringResource(id = R.string.add)
    ),
    modifier = modifier
)
```

Parameter | Default value | Description
-- | -- | --
`icon: OdsFloatingActionButtonIcon` | | Icon to be used into the FAB
`onClick: () -> Unit` | | Callback called on FAB click
`modifier: Modifier` | `Modifier` | `Modifier` to be applied to the FAB
`mini: Boolean` | `false` | Controls the size of the FAB. If `true`, the size of the FAB will be 40dp, otherwise the default size will be used.
{:.table}

### Mini FAB

A mini FAB should be used on smaller screens.

Mini FABs can also be used to create visual continuity with other screen elements.

![FAB mini light](images/fab_mini_light.png)  ![FAB mini dark](images/fab_mini_dark.png)

#### Jetpack Compose implementation

To display a mini FAB in your composable screen use `OdsFloatingActionButton`

```kotlin
OdsFloatingActionButton(
    onClick = {
        // Do something
    },
    mini = true,
    icon = OdsFloatingActionButtonIcon(
        painterResource(id = R.drawable.ic_plus),
        stringResource(id = R.string.add)
    ),
    modifier = modifier
)
```

Use the same API as the [Regular FAB](#regular-fab).

### Extended FAB

The extended FAB is wider, and it includes a text label.

![FAB extended light](images/fab_extended_light.png)  ![FAB extended dark](images/fab_extended_dark.png)

![FAB extended full width light](images/fab_extended_full_width_light.png)  ![FAB extended full width dark](images/fab_extended_full_width_dark.png)

#### Jetpack Compose implementation

To display an extended FAB, use `OdsExtendedFloatingActionButton`:

```kotlin
OdsExtendedFloatingActionButton(
    onClick = {
        // Do something       
    },
    text = stringResource(id = R.string.add),
    icon = OdsFloatingActionButtonIcon(painterResource(id = R.drawable.ic_plus), ""),
    modifier = modifier
)
```

Parameter | Default value | Description
-- | -- | --
`icon: OdsFloatingActionButtonIcon` | | Icon to be used into the FAB
`onClick: () -> Unit` | | Callback called on FAB click
`text: String` | | Text to be displayed into the FAB
`modifier: Modifier` | `Modifier` | `Modifier` to be applied to the FAB
{:.table}

## Component specific tokens

_Soon available_
