---
layout: detail
title: Colors
---

---

**Page Summary**

* [Specifications references](#specifications-references)
* [Implementation in Jetpack Compose](#implementation-in-jetpack-compose)
  * [Theme colors](#theme-colors)
  * [Functional colors](#functional-colors)
  * [Other colors from Orange Design System](#other-colors-from-orange-design-system)

---


## Specifications references

- [Design System Manager - Colour](https://system.design.orange.com/0c1af118d/p/623630-colour/b/041102)
- [Material Design - The color system](https://material.io/design/color/the-color-system.html#color-usage-and-palettes)

## Implementation in Jetpack Compose

### Theme colors

ODS library provides MaterialTheme colors. You can use these colors by using `MaterialTheme.colors`:

```kotlin
Text(
    text = "Hello world",
    color = MaterialTheme.colors.primary
)
```

### Functional colors

Functional colors have been added and can also be used as above:
- `MaterialTheme.colors.functionalPositive`
- `MaterialTheme.colors.functionalInfo`
- `MaterialTheme.colors.functionalAlert`

Note: These colors will be different depending on whether they are displayed in light or in dark mode.

### Other colors from Orange Design System

All the other colors defined in the library can be used directly through their names:

```kotlin
Text(
    text = "Hello world",
    color = Pink100
)
```
