---
layout: detail
title: Dialog
description: Dialogs inform users about a task and can contain critical information, require decisions, or involve multiple tasks.
---

A dialog is a type of modal window that appears in front of app content to
provide critical information or ask for a decision. Dialogs disable all app
functionality when they appear, and remain on screen until confirmed, dismissed,
or a required action has been taken.

Dialogs are purposefully interruptive, so they should be used sparingly.

**Contents**

*   [Using dialogs](#using-dialogs)
    *   [Material Design](#material-design)
    *   [Accessibility](#accessibility)
*   [Adding a dialog](#adding-a-dialog)
    *   [Alert dialog](#alert-dialog)
        * [Alert dialog in XML](#alert-dialog-in-xml)
        * [Alert dialog in Jetpack Compose](#alert-dialog-in-jetpack-compose)
    *   [Simple dialog](#simple-dialog)
        * [Simple dialog in XML](#simple-dialog-in-xml)


## Using dialogs

Before you can use Orange themed dialogs, you need to add a dependency to the Orange Design System
for Android library. For more information, go to the
[Getting started](../home_content.md) page.

### Material Design

Orange Dialogs are based on Material Design from Google and apply Orange theming.
**Note:** Here is the full documentation
of [Material Design Dialog](https://material.io/components/dialogs/)

### Accessibility

The contents within a dialog should follow their own accessibility guidelines,
such as an icon on a title having a content description via the
`android:contentDescription` attribute set in the
`MaterialAlertDialog.MaterialComponents.Title.Icon` style or descendant.

## Adding a dialog

### Alert dialog

#### Alert dialog in XML

Alert dialogs interrupt users with urgent information, details, or actions.

In code:

```kt
MaterialAlertDialogBuilder(context)
        .setTitle(resources.getString(R.string.title))
        .setMessage(resources.getString(R.string.supporting_text))
        .setNeutralButton(resources.getString(R.string.cancel)) { dialog, which ->
            // Respond to neutral button press
        }
        .setNegativeButton(resources.getString(R.string.decline)) { dialog, which ->
            // Respond to negative button press
        }
        .setPositiveButton(resources.getString(R.string.accept)) { dialog, which ->
            // Respond to positive button press
        }
        .show()
```

Centered dialog:

```kt
MaterialAlertDialogBuilder(context, R.style.Widget.Orange.Dialog.Centered)
        .setTitle("title")
        .setMessage("message")
        .setPositiveButton("positiveText", null)
        .setNegativeButton("negativeText", null)
        .setIcon(R.drawable.your_drawable)
        .show()
```

#### Alert dialog in Jetpack Compose

To display an alert dialog in your composable screen, you can use:

```kotlin
OdsAlertDialog(
    modifier = Modifier, // Optional, `Modifier` if not set
    titleText = "title", // Optional
    text = "content text of the dialog",
    confirmButtonText = "confirm",
    onConfirmButtonClick = { doSomething() },
    dismissButtonText = "dismiss", // Optional
    onDismissButtonClick = { doSomething() }, // Optional
    properties = DialogProperties() // Optional, `DialogProperties()` if not set
)
```

### Simple dialog

#### Simple dialog in XML

Simple dialogs can display items that are immediately actionable when selected.
They don’t have text buttons.

As simple dialogs are interruptive, they should be used sparingly.
Alternatively, dropdown menus provide options in a non-modal, less disruptive
way.

In code:

```kt
val items = arrayOf("Item 1", "Item 2", "Item 3")

MaterialAlertDialogBuilder(context)
        .setTitle(resources.getString(R.string.title))
        .setItems(items) { dialog, which ->
             // Respond to item chosen
         }
        .show()
```