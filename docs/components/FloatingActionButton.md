---
layout: detail
title: Floating Action Button
description: Floating Action Button description
---

# Floating action buttons

A floating action button (FAB) represents the primary action of a screen.

**Contents**

*   [Using FABs](#using-fabs)
*   [Regular FABs](#regular-fabs)
*   [Mini FABs](#mini-fabs)
*   [Extended FABs](#extended-fabs)

## Using fabs

Before you can use Orange themed FABs, you need to add a dependency to the Orange Design System
for Android library. For more information, go to the
[Getting started](../home_content.md) page.

### Material Design

Orange FABs are based on Material Design from Google and apply Orange theming.
**Note:** Here is the full documentation
of [Material Design FAB](https://material.io/components/buttons-floating-action-button/)

### Accessibility

You should set a content description on a FAB via the
`android:contentDescription` attribute or `setContentDescription` method so that
screen readers like TalkBack are able to announce their purpose or action. Text
rendered in Extended FABs is automatically provided to accessibility services,
so additional content labels are usually unnecessary.

### Visibility

Use the `show` and `hide` methods to animate the visibility of a
`FloatingActionButton` or an `ExtendedFloatingActionButton`. The show animation
grows the widget and fades it in, while the hide animation shrinks the widget
and fades it out.

```kt
// To show:
fab.show()
// To hide:
fab.hide()
```

### Extending and Shrinking

Use the `extend` and `shrink` methods to animate showing and hiding the text of
an `ExtendedFloatingActionButton`. The extend animation extends the FAB to show
the text and the icon. The shrink animation shrinks the FAB to show just the
icon.

```kt
// To extend:
extendedFab.extend()
// To shrink:
extendedFab.shrink()
```

### Sizing FABs

The `FloatingActionButton` can be sized either by using the discrete sizing
modes or a custom size.

There are three `app:fabSize` modes:

*   `normal` - the normal sized button, 56dp.
*   `mini` - the mini sized button, 40dp.
*   `auto` (default) - the button size will change based on the window size. For
    small sized windows (largest screen dimension < 470dp) this will select a
    mini sized button, and for larger sized windows it will select a normal
    sized button.

Or, you can set a custom size via the `app:fabCustomSize` attribute. If set,
`app:fabSize` will be ignored, unless the custom size is cleared via the
`clearCustomSize` method.

### Types

There are three types of FABS: 
-   [Regular FABs](#regular-fabs)
-   [Mini FABs](#mini-fabs)
-   [Extended FABs](#extended-fabs)

## Regular FABs

Regular FABs are FABs that are not expanded and are a regular size.

API and source code:

*   `FloatingActionButton`
  *   [Class description](https://developer.android.com/reference/com/google/android/material/floatingactionbutton/FloatingActionButton)
  *   [Class source](https://github.com/material-components/material-components-android/tree/master/lib/java/com/google/android/material/floatingactionbutton/FloatingActionButton.java)

### Using Regular FAB

To have a regular FAB in your layout you must add `com.google.android.material.floatingactionbutton.FloatingActionButton` in your layout.

In the layout:

```xml
<androidx.coordinatorlayout.widget.CoordinatorLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent">

  <!-- Main content -->

  <com.google.android.material.floatingactionbutton.FloatingActionButton
      android:id="@+id/floating_action_button"
      android:layout_width="wrap_content"
      android:layout_height="wrap_content"
      android:layout_gravity="bottom|end"
      android:layout_margin="16dp"
      android:contentDescription="@string/fab_content_desc"
      app:srcCompat="@drawable/ic_plus_24"/>

</androidx.coordinatorlayout.widget.CoordinatorLayout>
```

In code:

```kt
fab.setOnClickListener {
    // Respond to FAB click
}
```

## Mini FABs

A mini FAB should be used on smaller screens.

Mini FABs can also be used to create visual continuity with other screen elements.

API and source code:

*   `FloatingActionButton`
  *   [Class description](https://developer.android.com/reference/com/google/android/material/floatingactionbutton/FloatingActionButton)
  *   [Class source](https://github.com/material-components/material-components-android/tree/master/lib/java/com/google/android/material/floatingactionbutton/FloatingActionButton.java)

### Adding a Mini FAB

To have a mini FAB in your layout you must add `com.google.android.material.floatingactionbutton.FloatingActionButton` in your layout and set property `fabSize` to `mini`.

In the layout:

```xml
<androidx.coordinatorlayout.widget.CoordinatorLayout
    >

  <!-- Main content -->
  <com.google.android.material.floatingactionbutton.FloatingActionButton
      app:fabSize="mini"/>

</androidx.coordinatorlayout.widget.CoordinatorLayout>
```

In code:

```kt
fab.setOnClickListener {
    // Respond to FAB click
}
```

## Extended FABs

The extended FAB is wider, and it includes a text label.

API and source code:

*   `ExtendedFloatingActionButton`
  *   [Class description](https://developer.android.com/reference/com/google/android/material/floatingactionbutton/ExtendedFloatingActionButton)
  *   [Class source](https://github.com/material-components/material-components-android/tree/master/lib/java/com/google/android/material/floatingactionbutton/ExtendedFloatingActionButton.java)

### Adding an extended FAB

To have an extended FAB in your layout you must add `com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton` in your layout.
Icon should be set with property `icon`.

In the layout:

```xml
<androidx.coordinatorlayout.widget.CoordinatorLayout
    >

  <!-- Main content -->

  <com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton
    android:id="@+id/extended_fab"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content"
    android:layout_margin="16dp"
    android:layout_gravity="bottom|end"
    android:contentDescription="@string/extended_fab_content_desc"
    android:text="@string/extended_fab_label"
    app:icon="@drawable/ic_plus_24px"/>

</androidx.coordinatorlayout.widget.CoordinatorLayout>
```

In code:

```kt
extendedFab.setOnClickListener {
    // Respond to Extended FAB click
}
```