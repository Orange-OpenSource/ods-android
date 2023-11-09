---
layout: detail
title: Image item
description:
---

<br>**On this page**

* [Specifications references](#specifications-references)
* [Accessibility](#accessibility)
* [Implementation](#implementation)
    * [Jetpack Compose](#jetpack-compose)
        * [OdsImageItem API](#odsimageitem-api)

---

## Specifications references

- [Design System Manager - Image item](https://system.design.orange.com/0c1af118d/p/49434d-image-item)
- [Material Design - Image lists](https://m2.material.io/components/image-lists)

## Accessibility

Please follow [accessibility criteria for development](https://a11y-guidelines.orange.com/en/mobile/android/development/).

An image in an `OdsImageItem` should always be associated to a content description. This is the reason why the `OdsImageItem.Image` forces the developer to fill a content description parameter.

## Implementation

#### Jetpack Compose

You can use the `OdsImageItem` composable like this:

```kotlin
OdsImageItem(
    image = OdsImageItem.Image(
        painterResource(id = R.drawable.placeholder),
        "Picture content description"
    ),
    modifier = modifier,
    legendAreaDisplayType = OdsImageItem.LegendAreaDisplayType.Below,
    title = "Component Image item",
    icon = OdsImageItem.IconToggleButton(
        uncheckedIcon = OdsIconButton.Icon(
            painterResource(id = R.drawable.ic_heart_outlined),
            "Add to favourites"
        ),
        checkedIcon = OdsIconButton.Icon(
            painterResource(id = R.drawable.ic_heart),
            "Remove from favourites"
        ),
        checked = false,
        onCheckedChange = { doSomething() },
    ),
    onClick = { doSomething() }
)
```

##### OdsImageItem API

Parameter | Default&nbsp;value | Description
-- | -- | --
`image: OdsImageItem.Image` | | Image displayed into the item
`legendAreaDisplayType: OdsImageItem.LegendAreaDisplayType` | | Controls how the title and the icon are displayed relatively to the image. If set to `OdsImageItemLegendAreaDisplayType.None`, no legend area will be displayed.
`modifier: Modifier` | `Modifier` | `Modifier` applied to the image item
`title: String?` | `null` | Title displayed into the image item. It is linked to the image and displayed according to the `legendAreaDisplayType` value.
`icon: OdsImageItem.IconToggleButton` | `null` | Clickable icon displayed next to the `title`
`onClick: (() -> Unit)?` | `null` | Callback invoked on image item click
{:.table}
