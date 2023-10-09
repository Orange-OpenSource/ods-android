---
layout: detail
title: "Sheets: bottom"
description: Bottom Sheets are surfaces anchored to the bottom of the screen that present users supplement content.
---

Use Sheets bottom to:

* Display content that complements the screen’s primary content
* Expose all complements options

---

**Page Summary**

* [Specifications references](#specifications-references)
* [Accessibility](#accessibility)
* [Implementation](#implementation)
* [Component specific tokens](#component-specific-tokens)

---

## Specifications references

- [Design System Manager - Sheets](https://system.design.orange.com/0c1af118d/p/81f927-sheets-bottom/b/47b99b)
- [Material Design - Sheets: bottom](https://material.io/components/sheets-bottom)
- Technical documentation soon available

## Accessibility

Please follow [accessibility criteria for development](https://a11y-guidelines.orange.com/en/mobile/android/development/)

## Implementation

![BottomSheet light](images/sheetbottom_light.png) ![BottomSheet dark](images/sheetbottom_dark.png)

### Jetpack Compose

```kotlin
OdsBottomSheetScaffold(
    sheetContent = {
        // Put here the content of the sheet
    },
    modifier = Modifier,
    scaffoldState = rememberBottomSheetScaffoldState(),
    topBar = null,
    snackbarHost = {},
    floatingActionButton = {},
    floatingActionButtonPosition = FabPosition.End,
    sheetGesturesEnabled = true,
    sheetPeekHeight = 56.dp,
    content = {
        // Put here the screen content
    }
)
```

## Component specific tokens

_Soon available_