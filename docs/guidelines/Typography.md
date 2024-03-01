---
layout: detail
title: Typography
---

---

**Page Summary**

* [Specifications references](#specifications-references)
* [Implementation in Jetpack Compose](#implementation-in-jetpack-compose)
    * [OdsText composable](#odstext-composable)

---

## Specifications references

- [Design System Manager - Typography](https://system.design.orange.com/0c1af118d/p/90d1e5-typography)
- [Material Design - The type system](https://material.io/design/typography/the-type-system.html#type-scale)

## Implementation in Jetpack Compose

As ODS library supports multi-theme, the available typography is managed by a class (`OdsTypography`) which is instantiated by the theme itself. It has a default constructor with the values used by Orange.
The theme provides typography for the application which uses the ODS library. ODS library components relies on the `OdsTypography` defined by the used theme.

Through the `OdsTypography` class, you can set the `fontFamily` which will be applied to the typography text styles and you can define text styles you want to be in capitals by adding it into the `allCapsTextStyles` list.

We recommend you to use `OdsText` composable and provide an `OdsTextStyle` to display texts:

```kotlin
OdsText(
    text = "Hello",
    style = OdsTextStyle.BodyL,
)
```

However, you can directly use ODS typography in a `Text` composable as shown below:

```kotlin
Text(
    text = "Hello",
    style = OdsTheme.typography.bodyL,
)
```

### OdsText composable

ODS library also provides `OdsText` composable which is here to simplify the code you write.

The two differences between `OdsText` and `Text` are:

- `OdsText` uses theme text colors automatically taking into account the parameters provided like the `enabled` one,
- `OdsText` takes directly an `OdsTextSyle` as parameter and manage all caps text style if needed (an `OdsTextStyle` can be set all caps in the `OdsTypography` of the theme).

Example of use:

```kotlin
OdsText(
    text = "Big title",
    style = OdsTextStyle.HeadlineL
)
```
