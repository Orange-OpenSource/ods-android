---
layout: detail
title: List items
description: Lists are continuous, vertical indexes of text or images.
---

<br>**On this page**

* [Specifications references](#specifications-references)
* [Accessibility](#accessibility)
* [Variants](#variants)
    * [Single-line list item](#single-line-list-item)
        * [Jetpack Compose](#jetpack-compose)
    * [Two-line list](#two-line-list)
        * [Jetpack Compose](#jetpack-compose-1)
    * [Three-line list](#three-line-list)
        * [Jetpack Compose](#jetpack-compose-2)
* [OdsListItem API](#odslistitem-api)

---

## Specifications references

- [Design System Manager - Lists](https://system.design.orange.com/0c1af118d/p/09a804-lists/b/669743)
- [Material Design - Lists](https://material.io/components/lists/)

## Accessibility

Please follow [accessibility criteria for development](https://a11y-guidelines.orange.com/en/mobile/android/development/).

## Variants

### Single-line list item

There are multiple display possibilities for a single-line list, where leading can optionally be an icon, a circular, a square or a wide image.

Here are two examples:

- with a wide image and a checkbox

  ![Lists single-line wide image](images/lists_single_line_wide_image_light.png) ![Lists single-line wide image dark](images/lists_single_line_wide_image_dark.png)

- with a standard icon and a checkbox

  ![Lists single-line](images/lists_single_line_light.png) ![Lists single-line dark](images/lists_single_line_dark.png)

Please note that there is no start padding with wide images.

#### Jetpack Compose

The library offers the `OdsListItem` composable to display lists items.

The `OdsListItem` composable allows you to display a leading icon using the `leadingIcon` parameter of the `OdsListItem` method, as well as a trailing element (either a checkbox, a switch, a radio button, an icon or a caption text) using the `trailing` parameter.

```kotlin
OdsListItem(
    modifier = Modifier.clickable { doSomething() },
    text = "Primary text",
    leadingIcon = OdsListItem.LeadingIcon(
        OdsListItem.leadingIcon.Type.Icon,
        painterResource(id = R.drawable.ic_heart),
        "Heart"
    ),
    trailing = OdsListItem.TrailingCheckbox(checked) { checked != checked },
    divider = true
)
```

Use [OdsListItem API (signature with subtitle)](#signature-with-subtitle-1-or-2-lines-of-text).

### Two-line list

Like single-line list, two-line list leading can optionally be an icon, a circular, a square or a wide image.

Here are two examples:

- with a wide image and a checkbox

  ![Lists two-line wide image](images/lists_two_line_wide_image_light.png) ![Lists two-line wide image dark](images/lists_two_line_wide_image_dark.png)

- with a standard icon and a checkbox

  ![Lists two-line](images/lists_two_line_light.png) ![Lists two-line dark](images/lists_two_line_dark.png)

#### Jetpack Compose

The only difference with the single-line implementation is that the `subtitle` property of `OdsListItem` is not null.

```kotlin
OdsListItem(
    modifier = Modifier.clickable { doSomething() },
    text = "Primary text",
    subtitle = "Secondary text",
    leadingIcon = OdsListItem.LeadingIcon(
        OdsListItem.LeadingIcon.Type.CircularImage,
        painterResource(id = R.drawable.placeholder, "")
    ),
    trailing = OdsListItem.TrailingIcon(
        painterResource(id = R.drawable.ic_drag_handle),
        "Drag item"
    ),
    divider = true
)
```

Use [OdsListItem API (signature with subtitle)](#signature-with-subtitle-1-or-2-lines-of-text).

### Three-line list

Like single-line list, three-line list leading can optionally be an icon, a circular, a square or a wide image.

Here are two examples:

- with a wide image and a checkbox

  ![Lists three-line wide image](images/lists_three_line_wide_image_light.png) ![Lists three-line wide image dark](images/lists_three_line_wide_image_dark.png)

- with a standard icon and a checkbox

  ![Lists three-line](images/lists_three_line_light.png) ![Lists three-line dark](images/lists_three_line_dark.png)

#### Jetpack Compose

The difference with the two-line implementation is that we use the second signature of `OdsListItem` with a `description` parameter.

```kotlin
OdsListItem(
    modifier = Modifier.clickable { doSomething() },
    text = "Primary text",
    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor.",
    leadingIcon = OdsListItem.LeadingIcon(
        OdsListItem.LeadingIcon.Type.SquareImage,
        painter = painterResource(id = R.drawable.placeholder),
        ""
    ),
    trailing = OdsListItem.TrailingCaption("Caption"),
    divider = true
)
```

Use [OdsListItem API (signature with description)](#signature-with-description-3-lines-of-text).

## OdsListItem API

### Signature with subtitle: 1 or 2 lines of text

{:.table}

| Parameter                               | Default&nbsp;value | Description                                                                                                                               |
|-----------------------------------------|--------------------|-------------------------------------------------------------------------------------------------------------------------------------------|
| `text: String`                          |                    | The primary text of the list item                                                                                                         |
| `modifier: Modifier`                    | `Modifier`         | Modifier to be applied to the list item                                                                                                   |
| `leadingIcon: OdsListItem.LeadingIcon?` | `null`             | The leading supporting visual of the list item                                                                                            |
| `subtitle: String?`                     | `null`             | Text displayed on one line below the primary text of the list item                                                                        |
| `overlineText: String?`                 | `null`             | The text displayed above the primary text                                                                                                 |
| `trailing: OdsListItem.Trailing?`       | `null`             | The trailing content to display at the end of the list item                                                                               |
| `divider: Boolean`                      | `false`            | Whether or not a divider is displayed at the bottom of the list item                                                                      |
| `onClick: (() -> Unit)?`                | `null`             | Will be called when the user clicks the list item. This parameter only has an effect if trailing is `OdsListItem.TrailingIcon` or `null`. |

### Signature with description: 3 lines of text

{:.table}

| Parameter                               | Default&nbsp;value | Description                                                                                                                               |
|-----------------------------------------|--------------------|-------------------------------------------------------------------------------------------------------------------------------------------|
| `text: String`                          |                    | The primary text of the list item                                                                                                         |
| `description: String`                   |                    | Text displayed on two lines max below the primary text of the list item                                                                   |
| `modifier: Modifier`                    | `Modifier`         | Modifier to be applied to the list item                                                                                                   |
| `leadingIcon: OdsListItem.LeadingIcon?` | `null`             | The leading supporting visual of the list item                                                                                            |
| `overlineText: String?`                 | `null`             | The text displayed above the primary text                                                                                                 |
| `trailing: OdsListItem.Trailing?`       | `null`             | The trailing content to display at the end of the list item                                                                               |
| `divider: Boolean`                      | `false`            | Whether or not a divider is displayed at the bottom of the list item                                                                      |
| `onClick: (() -> Unit)?`                | `null`             | Will be called when the user clicks the list item. This parameter only has an effect if trailing is `OdsListItem.TrailingIcon` or `null`. |
