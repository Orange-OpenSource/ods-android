---
layout: detail
title: Navigation drawers
description: The navigation drawer slides in from the left when the nav icon is tapped. The content should be concerned with identity and/or navigation..
---

<br>**On this page**

* [Specifications references](#specifications-references)
* [Accessibility](#accessibility)
* [Implementation](#implementation)
    * [Jetpack Compose](#jetpack-compose)
        * [OdsModalDrawer API](#odsmodaldrawer-api)
        * [API Descriptors](#api-descriptors)
            * [OdsModalDrawerHeader](#odsmodaldrawerheader)
            * [OdsModalDrawerItem](#odsmodaldraweritem)

---

## Specifications references

- [Design System Manager - Navigation drawers](https://system.design.orange.com/0c1af118d/p/92bc26-navigation-drawers/b/146f55)
- [Material Design - Navigation drawer](https://m2.material.io/components/navigation-drawer)

## Accessibility

Please follow [accessibility criteria for development](https://a11y-guidelines.orange.com/en/mobile/android/development/).

## Implementation

### Jetpack Compose

The `OdsModalDrawer` is built using the following provided elements:

- `header` which contains a title, an optional subtitle and an optional image displayed as header background or as an avatar (circular shaped image) above the title.
- `items` which constitute the list of elements displayed vertically below the header. This list can contain section headers, list items or simple dividers.

You can use the `OdsModalDrawer` composable like this:

```kotlin
OdsModalDrawer(
    header = OdsModalDrawerHeader(
        title = "Side navigation drawer",
        image = OdsModalDrawerHeaderAvatar(painterResource(id = R.drawable.placeholder)),
        subtitle = "Example",
    ),
    items = listOf<OdsModalDrawerItem>(
        OdsModalDrawerListItem( // `OdsModalDrawerListItem` is used to specified an item of the list
            icon = R.drawable.ic_heart,
            text = "label1"
        ) { doSomething() },
        OdsModalDrawerListItem(
            icon = R.drawable.ic_heart,
            text = "label2"
        ) { doSomething() },
        OdsModalDrawerDivider, // `OdsModalDrawerDivider` is used to add a divider in a specific level of the list
        OdsModalDrawerSectionHeader(
            label = "Label"
        ), // `OdsModalDrawerSectionHeader` is used to add a divider and the text above the divider
        OdsModalDrawerListItem(
            icon = R.drawable.ic_heart,
            text = "label3"
        ) { doSomething() }
    ),
    drawerState = rememberDrawerState(DrawerValue.Closed), // or rememberDrawerState(DrawerValue.Open)
) {
    // Put here the rest of the UI content
}
```

#### OdsModalDrawer API

Parameter | Default&nbsp;value | Description
-- | -- | --
<b>`header: `</b>[OdsModalDrawerHeader](#odsmodaldrawerheader) | | Content descriptor of the drawer header
<b>`items: List<`</b>[OdsModalDrawerItem](#odsmodaldraweritem)<b>`>`</b> | | List of `OdsModalDrawerItem` displayed in a column inside the modal drawer
`modifier: Modifier` | `Modifier` | `Modifier` applied to the modal drawer
`state: DrawerState` | `rememberDrawerState(DrawerValue.Closed)` | State of the modal drawer
`selectedItem: OdsModalDrawerListItem?` | `null` | Selected `OdsModalDrawerListItem` that appears in selected state
`content: @Composable () -> Unit` | | Content of the rest of the UI
{:.table}

#### API descriptors

##### OdsModalDrawerHeader

Parameter | Default&nbsp;value | Description
-- | -- | --
<b>`title: String`</b> | | Title displayed in the header
`image: OdsModalDrawerHeaderImage?` | `null` | Image displayed in the header. It should be an avatar image of `OdsModalDrawerHeaderAvatar` type or a background image of `OdsModalDrawerHeaderBackground` type.
`subtitle: String?` | `null` | Subtitle displayed below the `title` in the header
{:.table}

##### OdsModalDrawerItem

Here are the available types of `OdsModalDrawerItem`:

**OdsModalDrawerSectionHeader** displays a divider and a section header label below

Parameter | Default&nbsp;value | Description
-- | -- | --
<b>`label: String`</b> | | Label of the section header
{:.table}

**OdsModalDrawerListItem** displays a clickable item in the modal drawer

Parameter | Default&nbsp;value | Description
-- | -- | --
<b>`text: String`</b> | | Text displayed in the modal drawer list item
`leadingIcon: Painter?` | `null` | Leading icon displayed in the modal drawer list item
`onClick: (OdsModalDrawerListItem) -> Unit` | | Callback invoked on an `OdsModalDrawerListItem` click. Provides the clicked `OdsModalDrawerListItem`.
{:.table}

**OdsModalDrawerDivider** displays a simple divider (no parameter needed)
