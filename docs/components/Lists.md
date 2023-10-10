---
layout: detail
title: Lists
description: Lists are continuous, vertical indexes of text or images.
---

<br>
**On this page**

* [Specifications references](#specifications-references)
* [Accessibility](#accessibility)
* [Variants](#variants)
    * [Single-line list](#single-line-list)
    * [Two-line list](#two-line-list)
    * [Three-line list](#three-line-list)
* [Component specific tokens](#component-specific-tokens)

---

## Specifications references

- [Design System Manager - Lists](https://system.design.orange.com/0c1af118d/p/09a804-lists/b/669743)
- [Material Design - Lists](https://material.io/components/lists/)

## Accessibility

Please follow [accessibility criteria for development](https://a11y-guidelines.orange.com/en/mobile/android/development/)

## Variants

### Single-line list

There are multiple display possibilities for a single-line list, where leading can optionally be an icon, a circular, a square or a wide image.

Here are two examples:

- with a wide image and a checkbox

  ![Lists single-line wide image](images/lists_single_line_wide_image_light.png) ![Lists single-line wide image dark](images/lists_single_line_wide_image_dark.png)

- with a standard icon and a checkbox

  ![Lists single-line](images/lists_single_line_light.png) ![Lists single-line dark](images/lists_single_line_dark.png)

Please note that there is no start padding with wide images.

#### Jetpack Compose implementation

The library offers the `OdsListItem` composable to display lists items.

**Leading icon:**

To specify an icon type, use the `Modifier.iconType` method on the `OdsListItem` modifier and call `OdsListItemScope.OdsListItemIcon` in the `icon` lambda.

**Trailing element:**

The `OdsListItem` composable allows you to display an `OdsListItemTrailing` (Checkbox, Switch, RadioButton, Icon or a Caption text) as trailing element. If this does not meet your
needs and only in this case, you can use the `OdsListItem` method signature which accept any Composable as trailing.

Note: The first signature (with `OdsListItemTrailing`) automatically manages accessibility, if you use the second one, don't forget to manage it on your side.

**Dividers:**

A divider can also be displayed at the bottom of the list item using the `Modifier.divider` method on the `OdsListItem` modifier.

```kotlin
OdsListItem(
    modifier = Modifier
        .clickable { doSomething() }
        .iconType(OdsListItemIconType.Icon)
        .divider(),
    text = "Primary text",
    icon = {
        OdsListItemIcon(
            painter = painterResource(id = R.drawable.ic_heart),
            contentDescription = "Heart"
        )
    },
    trailing = OdsCheckboxTrailing(checked = itemChecked)
)
```

### Two-line list

Like single-line list, two-line list leading can optionally be an icon, a circular, a square or a wide image.

Here are two examples:

- with a wide image and a checkbox

  ![Lists two-line wide image](images/lists_two_line_wide_image_light.png) ![Lists two-line wide image dark](images/lists_two_line_wide_image_dark.png)

- with a standard icon and a checkbox

  ![Lists two-line](images/lists_two_line_light.png) ![Lists two-line dark](images/lists_two_line_dark.png)

#### Jetpack Compose implementation

The only difference with the single-line implementation is that the `secondaryText` property of `OdsListItem` is not null.

```kotlin
OdsListItem(
    modifier = Modifier
        .clickable { doSomething() }
        .iconType(OdsListItemIconType.CircularImage)
        .divider(),
    text = "Primary text",
    secondaryText = "Secondary text",
    icon = { OdsListItemIcon(painter = painterResource(id = R.drawable.placeholder)) },
    trailing = OdsIconTrailing(
        painter = painterResource(id = R.drawable.ic_drag_handle),
        contentDescription = "Drag item"
    )
)
```

### Three-line list

Like single-line list, three-line list leading can optionally be an icon, a circular, a square or a wide image.

Here are two examples:

- with a wide image and a checkbox

  ![Lists three-line wide image](images/lists_three_line_wide_image_light.png) ![Lists three-line wide image dark](images/lists_three_line_wide_image_dark.png)

- with a standard icon and a checkbox

  ![Lists three-line](images/lists_three_line_light.png) ![Lists three-line dark](images/lists_three_line_dark.png)

#### Jetpack Compose implementation

The only difference with the two-line implementation is that the `singleLineSecondaryText` property of `OdsListItem` is `false`.

```kotlin
OdsListItem(
    modifier = Modifier
        .clickable { doSomething() }
        .iconType(OdsListItemIconType.SquareImage)
        .divider(),
    text = "Primary text",
    secondaryText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor.",
    singleLineSecondaryText = false,
    icon = { OdsListItemIcon(painter = painterResource(id = R.drawable.placeholder)) },
    trailing = OdsCaptionTrailing(text = "Caption")
)
```

## Component specific tokens

_Soon available_
