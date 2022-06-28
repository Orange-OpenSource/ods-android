---
layout: detail
title: Typography
---

---

**Page Summary**

* [Specifications references](#specifications-references)
* [Implementation in Jetpack Compose](#implementation-in-jetpack-compose)
  * [TextStyles](#textstyles)
  * [OdsText composables](#odstext-composables)

---

## Specifications references

- [Design System Manager - Typography](https://system.design.orange.com/0c1af118d/p/90d1e5-typography)
- [Material Design - The type system](https://material.io/design/typography/the-type-system.html#type-scale)

## Implementation in Jetpack Compose

### TextStyles

ODS library uses the Material type system.
TextStyles are accessed via `MaterialTheme.typography`. Retrieve the TextStyles like so:

```kotlin
Text(
    text = "Subtitle2 styled",
    style = MaterialTheme.typography.subtitle2
)
```

### OdsText composables

ODS library also provides `OdsText` composables already using specific styles. They are here to simplify the code you write.

For example, to display a text styled with subtitle2 typo, you can write:

```kotlin
OdsTextSubtitle2(text = "Subtitle2 styled")
```

Optional parameters are:
- a `Modifier`
- an `OdsDisplayAppearance` which allow to force elements appearance to be displayed on light or dark background.
