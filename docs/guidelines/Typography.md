---
layout: detail
title: Typography
---

## Using typography in Jetpack Compose

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
