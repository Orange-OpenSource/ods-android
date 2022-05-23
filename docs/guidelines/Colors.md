---
layout: detail
title: Colors
---

## Using colors in Jetpack Compose

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
