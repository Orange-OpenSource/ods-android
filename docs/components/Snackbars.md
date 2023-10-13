---
layout: detail
title: Snackbars
description: Snackbars provide brief messages about app processes at the bottom of the screen.
---

Snackbars inform users of a process that an app has performed or will perform.
They appear temporarily, towards the bottom of the screen. They shouldn’t
interrupt the user experience, and they don’t require user input to disappear.
They disappear either after a timeout or after a user interaction elsewhere on
the screen, but can also be swiped off the screen.

Snackbars can also offer the ability to perform an action, such as undoing an
action that was just taken, or retrying an action that had failed.

<br>**On this page**

* [Specifications references](#specifications-references)
* [Accessibility](#accessibility)
* [Implementation](#implementation)
    * [Jetpack Compose](#jetpack-compose)

---

## Specifications references

- [Design System Manager - Toasts & Snackbars](https://system.design.orange.com/0c1af118d/p/887440-toast--snackbars/b/043ece)
- [Material Design - Snackbars](https://material.io/components/snackbars)

## Accessibility

Please follow [accessibility criteria for development](https://a11y-guidelines.orange.com/en/mobile/android/development/)

Snackbars support content labeling for accessibility and are readable by most
screen readers, such as TalkBack. Text rendered in snackbars is automatically
provided to accessibility services. Additional content labels are usually
unnecessary.

## Implementation

![Snackbar light](images/snackbar_light.png)

![Snackbar dark](images/snackbar_dark.png)

With action button:

![Snackbar with action light](images/snackbar_with_action_light.png)

![Snackbar with action dark](images/snackbar_with_action_dark.png)

### Jetpack Compose

We advise you to use a `Scaffold` to add an `OdsSnackbar` in order to make sure everything is displayed together in the right place according to Material Design.
Then use `OdsSnackbarHost` which provides the good margins to display the snackbar and `OdsSnackbar` as follow:

```kotlin
val scaffoldState = rememberScaffoldState()
val coroutineScope: CoroutineScope = rememberCoroutineScope()

Scaffold(
    scaffoldState = scaffoldState,
    snackbarHost = {
        OdsSnackbarHost(hostState = it) { data ->
            OdsSnackbar(snackbarData = data)
        }
    }) {
    OdsButton(
        modifier = Modifier
            .padding(horizontal = dimensionResource(id = com.orange.ods.R.dimen.screen_horizontal_margin))
            .padding(top = dimensionResource(id = com.orange.ods.R.dimen.screen_vertical_margin)),
        text = "Show snackbar",
        onClick = {
            coroutineScope.launch {
                scaffoldState.snackbarHostState.showSnackbar(
                    message = "This is the message of the Snackbar.",
                    actionLabel = "Action"
                )
            }
        }
    )
}
```
