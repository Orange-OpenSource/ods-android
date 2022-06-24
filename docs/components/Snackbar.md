---
layout: detail
title: Snackbar
description: Snackbars provide brief messages about app processes at the bottom of the screen.
---

**Page Summary**

* [Specifications references](#specifications-references)
* [Accessibility](#accessibility)
* [Implementation](#implementation)
    * [Adding an action](#adding-an-action)
    * [Anchoring a snackbar](#anchoring-a-snackbar)


Snackbars inform users of a process that an app has performed or will perform.
They appear temporarily, towards the bottom of the screen. They shouldn’t
interrupt the user experience, and they don’t require user input to disappear.
They disappear either after a timeout or after a user interaction elsewhere on
the screen, but can also be swiped off the screen.

Snackbars can also offer the ability to perform an action, such as undoing an
action that was just taken, or retrying an action that had failed.

## Specifications references

- [Design System Manager - Toasts & Snackbars](https://system.design.orange.com/0c1af118d/p/887440-toast--snackbars/b/043ece)
- [Material Design - Snackbars](https://material.io/components/snackbars)

## Accessibility

Please follow [accessibility criteria for development](https://a11y-guidelines.orange.com/en/mobile/android/development/)

Snackbars support content labeling for accessibility and are readable by most
screen readers, such as TalkBack. Text rendered in snackbars is automatically
provided to accessibility services. Additional content labels are usually
unnecessary.

### Implementation

Calling `make` creates the snackbar, but doesn't cause it to be visible on the
screen. To show it, use the `show` method on the returned `Snackbar` instance.
Note that only one snackbar will be shown at a time. Showing a new snackbar will
dismiss any previous ones first.

To show a snackbar with a message and no action:

```kotlin
// The view used to make the snackbar.
// This should be contained within the view hierarchy you want to display the
// snackbar. Generally it can be the view that was interacted with to trigger
// the snackbar, such as a button that was clicked, or a card that was swiped.
val contextView = findViewById<View>(R.id.context_view)

Snackbar.make(contextView, R.string.text_label, Snackbar.LENGTH_SHORT)
    .show()
```

#### Adding an action

To add an action, use the `setAction` method on the object returned from `make`.
Snackbars are automatically dismissed when the action is clicked.

To show a snackbar with a message and an action:

```kotlin
Snackbar.make(contextView, R.string.text_label, Snackbar.LENGTH_LONG)
    .setAction(R.string.action_text) {
        // Responds to click on the action
    }
    .show()
```

#### Anchoring a snackbar

By default, `Snackbar`s will be anchored to the bottom edge of their parent
view. However, you can use the `setAnchorView` method to make a `Snackbar`
appear above a specific view within your layout, e.g. a `FloatingActionButton`.

```kotlin
Snackbar.make(...)
    .setAnchorView(fab)
    ...
```

This is especially helpful if you would like to place a `Snackbar` above
navigational elements at the bottom of the screen, such as a `BottomAppBar` or
`BottomNavigationView`.

