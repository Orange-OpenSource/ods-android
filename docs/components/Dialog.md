---
layout: detail
title: Dialog
description: Dialog description
---

# Dialogs

Dialogs inform users about a task and can contain critical information, require decisions, or involve multiple tasks.

A dialog is a type of modal window that appears in front of app content to
provide critical information or ask for a decision. Dialogs disable all app
functionality when they appear, and remain on screen until confirmed, dismissed,
or a required action has been taken.

Dialogs are purposefully interruptive, so they should be used sparingly.

**Contents**

*   [Using dialogs](#using-dialogs)
*   [Alert dialog](#alert-dialog)
*   [Simple dialog](#simple-dialog)

## Using dialogs

Before you can use Orange themed dialogs, you need to add a dependency to the Orange Design System
for Android library. For more information, go to the
[Getting started](../getting-started.md) page.

### Material Design

Orange Dialogs are based on Material Design from Google and apply Orange theming.
**Note:** Here is the full documentation
of [Material Design Dialog](https://material.io/components/dialogs/)

### Accessibility

The contents within a dialog should follow their own accessibility guidelines,
such as an icon on a title having a content description via the
`android:contentDescription` attribute set in the
`MaterialAlertDialog.MaterialComponents.Title.Icon` style or descendant.

### Types

There are three types of dialogs: 
-   [Alert dialog](#alert-dialog)
-   [Simple dialog](#simple-dialog)

## Alert dialog

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

## Simple dialog

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

## Centered dialog

```kt
MaterialAlertDialogBuilder(context, R.style.Widget.Orange.Dialog.Centered)
        .setTitle("title")
        .setMessage("message")
        .setPositiveButton("positiveText", null)
        .setNegativeButton("negativeText", null)
        .setIcon(R.drawable.your_drawable)
        .show()
```