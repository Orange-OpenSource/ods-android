---
layout: detail
title: Snackbar
description: Snackbar description
---

# Snackbars

Snackbars provide brief messages about app processes at the bottom of the screen.

Snackbars inform users of a process that an app has performed or will perform.
They appear temporarily, towards the bottom of the screen. They shouldn’t
interrupt the user experience, and they don’t require user input to disappear.
They disappear either after a timeout or after a user interaction elsewhere on
the screen, but can also be swiped off the screen.

Snackbars can also offer the ability to perform an action, such as undoing an
action that was just taken, or retrying an action that had failed.

**Contents**

*   [Using snackbars](#using-snackbars)

## Using snackbars

Before you can use Orange themed snackbars, you need to add a dependency to the Orange Design System
for Android library. For more information, go to the
[Getting started](../getting-started.md) page.

### Material Design

Orange Buttons are based on Material Design from Google and apply Orange theming.
**Note:** Here is the full documentation
of [Material Design Snackbar](https://material.io/components/snackbars/)

### Accessibility

Snackbars support content labeling for accessibility and are readable by most
screen readers, such as TalkBack. Text rendered in snackbars is automatically
provided to accessibility services. Additional content labels are usually
unnecessary.

### Showing a snackbar

Calling `make` creates the snackbar, but doesn't cause it to be visible on the
screen. To show it, use the `show` method on the returned `Snackbar` instance.
Note that only one snackbar will be shown at a time. Showing a new snackbar will
dismiss any previous ones first.

To show a snackbar with a message and no action:

```kt
// The view used to make the snackbar.
// This should be contained within the view hierarchy you want to display the
// snackbar. Generally it can be the view that was interacted with to trigger
// the snackbar, such as a button that was clicked, or a card that was swiped.
val contextView = findViewById<View>(R.id.context_view)

Snackbar.make(contextView, R.string.text_label, Snackbar.LENGTH_SHORT)
    .show()
```

### Adding an action

To add an action, use the `setAction` method on the object returned from `make`.
Snackbars are automatically dismissed when the action is clicked.

To show a snackbar with a message and an action:

```kt
Snackbar.make(contextView, R.string.text_label, Snackbar.LENGTH_LONG)
    .setAction(R.string.action_text) {
        // Responds to click on the action
    }
    .show()
```

### Anchoring a snackbar

By default, `Snackbar`s will be anchored to the bottom edge of their parent
view. However, you can use the `setAnchorView` method to make a `Snackbar`
appear above a specific view within your layout, e.g. a `FloatingActionButton`.

```kt
Snackbar.make(...)
    .setAnchorView(fab)
    ...
```

This is especially helpful if you would like to place a `Snackbar` above
navigational elements at the bottom of the screen, such as a `BottomAppBar` or
`BottomNavigationView`.

