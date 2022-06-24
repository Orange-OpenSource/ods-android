---
layout: detail
title: Lists
description: Lists are continuous, vertical indexes of text or images.
---

**Page Summary**

* [Specifications references](#specifications-references)
* [Accessibility](#accessibility)
* [Variants](#variants)
  * [One-line list](#one-line-list)
  * [Two-line list](#two-line-list)
  * [Three-line list](#three-line-list)

## Specifications references

- [Design System Manager - Lists](https://system.design.orange.com/0c1af118d/p/09a804-lists/b/669743)
- [Material Design - Lists](https://material.io/components/lists/)

## Accessibility

Please follow [accessibility criteria for development](https://a11y-guidelines.orange.com/en/mobile/android/development/)

## Variants

### One-line list

There are two different displays for a one-line list:

1. with a wide thumbnail (without start padding)

    ![Lists one-line wide thumbnail](images/lists_one_line_wide_thumbnail_light.png) ![Lists one-line wide thumbnail dark](images/lists_one_line_wide_thumbnail_dark.png)

2. standard (all other cases)

    ![Lists one-line](images/lists_one_line_light.png) ![Lists one-line dark](images/lists_one_line_dark.png)

> **Jetpack Compose implementation**

The library offers 2 composables to display lists items. If you need to display a wide thumbnail without start padding in your item use `OdsListItemWideThumbnail`.  
In all other cases use `OdsListItem`.

```kotlin
    OdsListItem(
        modifier = Modifier.clickable { doSomething() },
        text = "Primary text",
        icon = { Icon(painter = painterResource(id = R.drawable.ic_heart), contentDescription = "Heart") },
        isThumbnailIcon = false
    )
```

To display a **one-line list item with a wide thumbnail** with a checkbox trailing element:

```kotlin
        OdsListItemWideThumbnail(
            modifier = Modifier.clickable { doSomething() },
            text = "Text",
            thumbnail = painterResource(id = R.drawable.placeholder),
            trailing = { OdsCheckbox(checked = itemChecked, onCheckedChange = { itemChecked = it }) }
        )
```

> **XML implementation**

*Not available yet*

### Two-line list

There are two different displays for a two-line list:

1. with a wide thumbnail (without start padding)

  ![Lists two-line wide thumbnail](images/lists_two_line_wide_thumbnail_light.png) ![Lists two-line wide thumbnail dark](images/lists_two_line_wide_thumbnail_dark.png)

2. standard (all other cases)

  ![Lists two-line](images/lists_two_line_light.png) ![Lists two-line dark](images/lists_two_line_dark.png)

> **Jetpack Compose implementation**

The library offers 2 composables to display lists items. If you need to display a wide thumbnail without start padding in your item use `OdsListItemWideThumbnail`.  
In all other cases use `OdsListItem`.

Here is an example of two-line list item with an optional icon (here an `OdsImageCircleShape` provided by the library) and an optional trailing (here a drag Icon).

```kotlin
        OdsListItem(
            modifier = Modifier.clickable { doSomething() },
            text = "Primary text",
            secondaryText = "Secondary text",
            icon = { OdsImageCircleShape(painter = painterResource(R.drawable.placeholder)) },
            trailing = { Icon(painter = painterResource(id = R.drawable.ic_drag_handle), contentDescription = "Drag item") }
        )
```

To display a **two-line list item with a wide thumbnail** with a drag icon trailing element:

```kotlin
        OdsListItemWideThumbnail(
            modifier = Modifier.clickable { doSomething() },
            text = "Text",
            secondaryText = "Secondary text",
            thumbnail = painterResource(id = R.drawable.placeholder),
            trailing = { Icon(painter = painterResource(id = R.drawable.ic_drag_handle), contentDescription = "Drag item") }
        )
```

> **XML implementation**

*Not available yet*

### Three-line list

There are two different displays for a two-line list:

1. with a wide thumbnail (without start padding)

  ![Lists three-line wide thumbnail](images/lists_three_line_wide_thumbnail_light.png) ![Lists three-line wide thumbnail dark](images/lists_three_line_wide_thumbnail_dark.png)

2. standard (all other cases)

  ![Lists three-line](images/lists_three_line_light.png) ![Lists three-line dark](images/lists_three_line_dark.png)

> **Jetpack Compose implementation**

The library offers 2 composables to display lists items. If you need to display a wide thumbnail without start padding in your item use `OdsListItemWideThumbnail`.  
In all other cases use `OdsListItem`.

Here is an example of three-line list item with an optional icon (here an `OdsListSquaredThumbnail` provided by the library) and an optional trailing (here a simple Text):

```kotlin
        OdsListItem(
            modifier = Modifier.clickable { doSomething() },
            text = "Primary text",
            secondaryText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor.",
            singleLineSecondaryText = false,
            icon = { OdsListSquaredThumbnail(painter = painterResource(R.drawable.placeholder)) },
            trailing = { Text(text = "Caption") }
        )
```

To display a **three-line list item with a wide thumbnail** with a text as trailing element:

```kotlin
        OdsListItemWideThumbnail(
            modifier = Modifier.clickable { doSomething() },
            text = "Text",
            secondaryText = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor.",
            singleLineSecondaryText = false,
            thumbnail = painterResource(id = R.drawable.placeholder),
            trailing = { Text(text = "Caption") }
        )
```

> **XML implementation**

*Not available yet*
