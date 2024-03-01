---
layout: detail
title: Colors
---

---

<br>**On this page**

* Table of contents
{:toc}

---

## Specifications references

- [Design System Manager - Colour](https://system.design.orange.com/0c1af118d/p/623630-colour/b/041102)
- [Material Design - The color system](https://material.io/design/color/the-color-system.html#color-usage-and-palettes)

## Implementation in Jetpack Compose

As ODS library supports multi-theme, the available colors are managed by a class (`OdsColors`) which is instantiated by the theme itself. In other words, the theme provides colors for the application which uses the ODS library. ODS library components relies on the `OdsColors` defined by the used theme.

The `OdsColors` class contains Material colors, functional colors and some specific components colors which allow the override of default Material behavior. For example, in Material a top app bar has a primary color background. With ODS library you can choose another color for your top app bar background and this will be defined in your theme components colors.

ODS library colors can be used by calling `OdsTheme.colors`:

```kotlin
Icon(
    painter = painterResource(id = R.drawable.ic_chevron_down),
    contentDescription = null,
    tint = OdsTheme.colors.onSurface
)
```

You can use the following **functional colors** in your application:

- `OdsTheme.colors.functional.positive` and `OdsTheme.colors.functional.onPositive`
- `OdsTheme.colors.functional.info`
- `OdsTheme.colors.functional.alert`

Note that all colors will be different depending on whether they are displayed in light or in dark mode.
Keep in mind that each theme provides colors for light and dark mode so avoid to use "static" colors directly like `Orange100` in your code cause the orange will be probably not the same in light and dark to keep an high contrast in the two modes.
