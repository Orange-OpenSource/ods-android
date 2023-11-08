---
layout: detail
title: "Sheets: bottom"
description: Bottom Sheets are surfaces anchored to the bottom of the screen that present users supplement content.
---

Use Sheets bottom to:

* Display content that complements the screen’s primary content
* Expose all complements options

<br>**On this page**

* [Specifications references](#specifications-references)
* [Accessibility](#accessibility)
* [Implementation](#implementation)
    * [Jetpack Compose](#jetpack-compose)
        * [OdsBottomSheetScaffold API](#odsbottomsheetscaffold-api-)

---

## Specifications references

- [Design System Manager - Sheets](https://system.design.orange.com/0c1af118d/p/81f927-sheets-bottom/b/47b99b)
- [Material Design - Sheets: bottom](https://material.io/components/sheets-bottom)

## Accessibility

Please follow [accessibility criteria for development](https://a11y-guidelines.orange.com/en/mobile/android/development/).

## Implementation

![BottomSheet light](images/sheetbottom_light.png) ![BottomSheet dark](images/sheetbottom_dark.png)

The contents within a bottom sheet should follow their own accessibility guidelines, such as images having content descriptions set on them.

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

#### OdsBottomSheetScaffold API [#](#odsbottomsheetscaffold-api-)

Parameter | Default&nbsp;value | Description
-- | -- | --
<b>`sheetContent: @Composable ColumnScope.() -> Unit`</b> | | Content of the bottom sheet
`modifier: Modifier` | `Modifier` | `Modifier` applied to the bottom sheet scaffold
`scaffoldState: BottomSheetScaffoldState` | `rememberBottomSheetScaffoldState()` | State of the scaffold
`topBar: (@Composable () -> Unit)?` | `null` | Top app bar displayed in the scaffold
`snackbarHost: @Composable (SnackbarHostState) -> Unit` | `{ SnackbarHost(it) }` | Composable hosting the snackbars shown inside the scaffold
`floatingActionButton: (@Composable () -> Unit)?` | `null` | Floating action button displayed in the scaffold
`floatingActionButtonPosition: FabPosition` | `FabPosition.End`| Position of the floating action button
`sheetGesturesEnabled: Boolean` | `true` | Whether the bottom sheet can be interacted with by gestures
`sheetPeekHeight: Dp` | `BottomSheetScaffoldDefaults.SheetPeekHeight` | Height of the bottom sheet when it is collapsed
<b>`content: @Composable (PaddingValues) -> Unit`</b> | | Content of the screen
{:.table}
