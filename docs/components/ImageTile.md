---
layout: detail
title: Image Tile
description:
---

<br>
**On this page**

* [Specifications references](#specifications-references)
* [Accessibility](#accessibility)
* [Implementation](#implementation)
* [Component specific tokens](#component-specific-tokens)

---

## Specifications references

- [Design System Manager - Image Tile](https://system.design.orange.com/0c1af118d/p/49434d-image-item)
- [Material Design - Image lists](https://m2.material.io/components/image-lists)

## Accessibility

Please follow [accessibility criteria for development](https://a11y-guidelines.orange.com/en/mobile/android/development/)

An image in an `OdsImageTile` should always be associated to a content description. This is the reason why the `OdsImageTileImage` forces the developer to fill a content description parameter.

## Implementation

#### Jetpack Compose

You can use the `OdsImageTile` composable like this:

```kotlin
OdsImageTile(
    image = OdsImageTileImage(
        painterResource(id = R.drawable.placeholder),
        "Picture content description"
    ),
    modifier = modifier,
    captionDisplayType = OdsImageTileCaptionDisplayType.Overlay,
    title = "Component Image Tile",
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
        onCheckedChange = { doSomething() },
    ),
    onClick = { doSomething() }
)
```

##### OdsImageTile API

Parameter | Default&nbsp;value | Description
-- | -- | --
`image: OdsImageTileImage` | | Image displayed into the tile
`legendAreaDisplayType: OdsImageTileLegendAreaDisplayType` | | Controls how the title and the icon are displayed relatively to the image. If set to `OdsImageTileLegendAreaDisplayType.None`, no legend area will be displayed.
`modifier: Modifier` | `Modifier` | `Modifier` applied to the image tile
`title: String?` | `null` | Title displayed into the tile. It is linked to the image and displayed according to the `legendAreaDisplayType` value.
`icon: OdsImageTileIconToggleButton` | `null` | Clickable icon displayed next to the `title`
`onClick: (() -> Unit)?` | `null` | Callback invoked on tile click
{:.table}

## Component specific tokens

_Soon available_