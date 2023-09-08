---
layout: detail
title: Image Tile
description:
---

---

**Page Summary**

* [Specifications references](#specifications-references)
* [Accessibility](#accessibility)
* [Implementation](#implementation)
* [Component specific tokens](#component-specific-tokens)

---

## Specifications references

- [Design System Manager - Image Tile](https://system.design.orange.com/0c1af118d/p/49434d-image-item)
- [Material Design - Image lists](https://m2.material.io/components/image-lists)
- Technical documentation soon available

## Accessibility

Please follow [accessibility criteria for development](https://a11y-guidelines.orange.com/en/mobile/android/development/)

## Implementation

> **Jetpack Compose implementation**

You can use the `OdsImageTile` composable like this:

```kotlin
OdsImageTile(
    image = OdsImageTileImage(
        painterResource(id = R.drawable.placeholder),
        "Picture content description"
    ),
    modifier = modifier,
    captionDisplayType = OdsImageTileCaptionDisplayType.Overlay,
    title = "Component Image Tile", // Optional 
    icon = OdsImageTileIconToggleButton(
        uncheckedIcon = OdsIconButtonIcon(
            painterResource(id = R.drawable.ic_heart_outlined),
            "Add to favourites"
        ),
        checkedIcon = OdsIconButtonIcon(
            painterResource(id = R.drawable.ic_heart),
            "Remove from favourites"
        ),
        checked = false,
        onCheckedChange = { },
    ), // Optional
    onClick = { } // Optional
)
```

## Component specific tokens

_Soon available_