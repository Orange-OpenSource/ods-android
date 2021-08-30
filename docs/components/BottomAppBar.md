---
layout: detail
title: Bottom App bar
description: Bottom App bar description
---

# Bottom app bar

A bottom app bar displays navigation and key actions at the bottom of mobile screens..

**Contents**

* [Using bottom app bar](#using-bottom-app-bar)

## Using bottom app bar

Before you can use Orange themed bottom app bar, you need to add a dependency to the Orange Design System
for Android library. For more information, go to the
[Getting started](../getting-started.md) page.

### Material Design

Orange Bottom app bar is based on Material Design from Google and apply Orange theming.
**Note:** Here is the full documentation
of [Material Design Bottom App Bar](https://material.io/components/app-bars-bottom/)

### Accessibility

Android's bottom app bar component APIs provide support for the navigation icon,
action items, overflow menu and more for informing the user as to what each
action performs. While optional, their use is strongly encouraged.

#### Content descriptions

When using icons for navigation icons, action items and other elements of bottom
app bars, you should set a content description on them so that screen readers
like TalkBack are able to announce their purpose or action, if any.

For an overall content description of the bottom app bar, set an
`android:contentDescription` or use the `setContentDescription` method on the
`BottomAppBar`.

For the navigation icon, this can be achieved via the
`app:navigationContentDescription` attribute or
`setNavigationContentDescription` method.

For action items and items within the overflow menu, the content description
needs to be set in the menu:

```xml
<menu>
    <item
          android:contentDescription="@string/content_description_one" />
    <item
          android:contentDescription="@string/content_description_two" />
</menu>
```

### Bottom app bar example

Bottom app bars provide access to a bottom navigation drawer and up to four
actions, including the floating action button.

API and source code:

*   `CoordinatorLayout`
    *   [Class definition](https://developer.android.com/reference/androidx/coordinatorlayout/widget/CoordinatorLayout)
*   `BottomAppBar`
    *   [Class definition](https://developer.android.com/reference/com/google/android/material/bottomappbar/BottomAppBar)
    *   [Class source](https://github.com/material-components/material-components-android/tree/master/lib/java/com/google/android/material/bottomappbar/BottomAppBar.java)
*   `FloatingActionButton`
    *   [Class definition](https://developer.android.com/reference/com/google/android/material/floatingactionbutton/FloatingActionButton)
    *   [Class source](https://github.com/material-components/material-components-android/tree/master/lib/java/com/google/android/material/floatingactionbutton/FloatingActionButton.java)

In the layout:

```xml
<androidx.coordinatorlayout.widget.CoordinatorLayout
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <!-- Note: A RecyclerView can also be used -->
    <androidx.core.widget.NestedScrollView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:paddingBottom="100dp"
        android:clipToPadding="false">

        <!-- Scrollable content -->

    </androidx.core.widget.NestedScrollView>

    <com.google.android.material.bottomappbar.BottomAppBar
        android:id="@+id/bottomAppBar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:layout_gravity="bottom"
        app:navigationIcon="@drawable/ic_menu_24dp"
        app:menu="@menu/bottom_app_bar"
        />

    <com.google.android.material.floatingactionbutton.FloatingActionButton
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        app:srcCompat="@drawable/ic_add_24dp"
        app:layout_anchor="@id/bottomAppBar"
        />

</androidx.coordinatorlayout.widget.CoordinatorLayout>
```

In `menu/bottom_app_bar.xml`:

```xml
<menu>

    <item
        android:id="@+id/search"
        android:icon="@drawable/ic_search_24dp"
        android:title="@string/search"
        android:contentDescription="@string/content_description_search"
        app:showAsAction="ifRoom"
        />

    <item
        android:id="@+id/more"
        android:title="@string/more"
        android:contentDescription="@string/content_description_more"
        app:showAsAction="never"
        />

</menu>
```

In menu/navigation icon drawables:

```xml
<vector
    android:tint="?attr/colorControlNormal">
</vector>
```

In code:

```kt
bottomAppBar.setNavigationOnClickListener {
    // Handle navigation icon press
}

bottomAppBar.setOnMenuItemClickListener { menuItem ->
    when (menuItem.itemId) {
        R.id.search -> {
            // Handle search icon press
            true
        }
        R.id.more -> {
            // Handle more item (inside overflow menu) press
            true
        }
        else -> false
    }
}
```