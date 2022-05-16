---
layout: detail
title: Text field
description: Text field description
---

# Text fields

Text fields let users enter and edit text.

**Contents**

* [Using text fields](#using-text-fields)
    * [Material Design](#material-design)
    * [Accessibility](#accessibility)
      * [Content description](#content-description)
      * [Custom `EditText`](#custom-edittext)
* [Adding a text field](#adding-a-text-field)
    * [Filled text field](#filled-text-field)
      * [Filled text field in XML](#filled-text-field-in-xml)
      * [Filled text field in Jetpack Compose](#filled-text-field-in-jetpack-compose)
    * [Outlined text field](#outlined-text-field)
      * [Outlined text field in XML](#outlined-text-field-in-xml)
      * [Outlined text field in Jetpack Compose](#outlined-text-field-in-jetpack-compose)

## Using text fields

Before you can use Orange themed text fields, you need to add a dependency to the Orange Design
System for Android library. For more information, go to the
[Getting started](../getting-started.md) page.

### Material Design

Orange Text fields are based on Material Design from Google and apply Orange theming.
**Note:** Here is the full documentation
of [Material Design Text field](https://material.io/components/text-fields/)

### Accessibility

Android's text field component APIs support both label text and helper text for informing the user
as to what information is requested for a text field. While optional, their use is strongly
encouraged.

#### Content description

When using **custom icons**, you should set a content description on them so that screen readers
like TalkBack are able to announce their purpose or action, if any.

For the leading icon, that can be achieved via the
`app:startIconContentDescription` attribute or `setStartIconContentDescription`
method. For the trailing icon, that can be achieved via the
`app:endIconContentDescription` attribute or `setEndIconContentDescription`
method.

When setting an **error message** that contains special characters that screen readers or other
accessibility systems are not able to read, you should set a content description via
the `app:errorContentDescription` attribute or
`setErrorContentDescription` method. That way, when the error needs to be announced, it will
announce the content description instead.

#### Custom `EditText`

If you are using a custom `EditText` as `TextInputLayout`'s child and your text field requires
different accessibility support than the one offered by
`TextInputLayout`, you can set a `TextInputLayout.AccessibilityDelegate` via the
`setTextInputAccessibilityDelegate` method. This method should be used in place of providing
an `AccessibilityDelegate` directly on the `EditText`.

### Adding a text field

#### Filled text field

Filled text fields have more visual emphasis than outlined text fields, making them stand out when
surrounded by other content and components.

  ![TextField filled](images/textfield_filled_light.png)  
  ![TextField filled dark](images/textfield_filled_dark.png)

##### Filled text field in XML

_**Note:** The filled text field is the default style if the style is not set._

**Filled text field examples**

API and source code:

* `TextInputLayout`
* [Class definition](https://developer.android.com/reference/com/google/android/material/textfield/TextInputLayout)
* [Class source](https://github.com/material-components/material-components-android/tree/master/lib/java/com/google/android/material/textfield/TextInputLayout.java)
* `TextInputEditText`
* [Class definition](https://developer.android.com/reference/com/google/android/material/textfield/TextInputEditText)
* [Class source](https://github.com/material-components/material-components-android/tree/master/lib/java/com/google/android/material/textfield/TextInputEditText.java)

In the layout:

```xml

<com.google.android.material.textfield.TextInputLayout
    android:id="@+id/filledTextField"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="@string/label">

    <com.google.android.material.textfield.TextInputEditText
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

</com.google.android.material.textfield.TextInputLayout>
```

In code:

```kt
// Get input text
val inputText = filledTextField.editText?.text.toString()

filledTextField.editText?.doOnTextChanged { inputText, _, _, _ ->
    // Respond to input text change
}
```

##### Filled text field in Jetpack Compose

To add a filled text field in your composable screen you can use:

```kotlin
var text by rememberSaveable { mutableStateOf("") }
OdsTextField(
    leadingIcon = painterResource(id = R.drawable.ic_heart), // Optional
    leadingIconContentDescription = "Like", // Optional
    onLeadingIconClick = { doSomething() }, // Optional
    enabled = true, // true if not set
    readOnly = false, // false if not set
    isError = false, // false if not set
    value = text,
    onValueChange = { text = it },
    label = "Label", // Optional
    placeholder = "Placeholder", // Optional
    trailingIcon = painterResource(id = R.drawable.ic_eye), // Optional
    trailingIconContentDescription = "Show password", // Optional
    onTrailingIconClick = { doSomething() }, // Optional
    trailingText = "units", // Optional
    visualTransformation = VisualTransformation.None, // `VisualTransformation.None` if not set
    keyboardOptions = KeyboardOptions.Default, // `KeyboardOptions.Default` if not set
    keyboardActions = KeyboardActions(), // `KeyboardActions()` if not set
    singleLine = false, // false if not set
    maxLines = Int.MAX_VALUE, // `Int.MAX_VALUE` if not set
)
```

#### Outlined text field

Outlined text fields have less visual emphasis than filled text fields. When they appear in places
like forms, where many text fields are placed together, their reduced emphasis helps simplify the
layout.

  ![TextField outlined](images/textfield_outlined_light.png)  
  ![TextField outlined dark](images/textfield_outlined_dark.png)

##### Outlined text field in XML

**Outlined text field examples**

API and source code:

* `TextInputLayout`
* [Class definition](https://developer.android.com/reference/com/google/android/material/textfield/TextInputLayout)
* [Class source](https://github.com/material-components/material-components-android/tree/master/lib/java/com/google/android/material/textfield/TextInputLayout.java)
* `TextInputEditText`
* [Class definition](https://developer.android.com/reference/com/google/android/material/textfield/TextInputEditText)
* [Class source](https://github.com/material-components/material-components-android/tree/master/lib/java/com/google/android/material/textfield/TextInputEditText.java)

In the layout:

```xml

<com.google.android.material.textfield.TextInputLayout 
    android:id="@+id/outlinedTextField"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:hint="@string/label"
    style="@style/Widget.MaterialComponents.TextInputLayout.OutlinedBox">

    <com.google.android.material.textfield.TextInputEditText 
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

</com.google.android.material.textfield.TextInputLayout>
```

In code:

```kt
// Get input text
val inputText = outlinedTextField.editText?.text.toString()

outlinedTextField.editText?.doOnTextChanged { inputText, _, _, _ ->
    // Respond to input text change
}
```

##### Outlined text field in Jetpack Compose

To add an outlined text field in your composable screen you can use:

```kotlin
var text by rememberSaveable { mutableStateOf("") }
OdsOutlinedTextField(
    leadingIcon = painterResource(id = R.drawable.ic_heart), // Optional
    leadingIconContentDescription = "Like", // Optional
    onLeadingIconClick = { doSomething() }, // Optional
    enabled = true, // true if not set
    readOnly = false, // false if not set
    isError = false, // false if not set
    value = text,
    onValueChange = { text = it },
    label = "Label", // Optional
    placeholder = "Placeholder", // Optional
    trailingIcon = painterResource(id = R.drawable.ic_eye), // Optional
    trailingIconContentDescription = "Show password", // Optional
    onTrailingIconClick = { doSomething() }, // Optional
    trailingText = "units", // Optional
    visualTransformation = VisualTransformation.None, // `VisualTransformation.None` if not set
    keyboardOptions = KeyboardOptions.Default, // `KeyboardOptions.Default` if not set
    keyboardActions = KeyboardActions(), // `KeyboardActions()` if not set
    singleLine = false, // false if not set
    maxLines = Int.MAX_VALUE, // `Int.MAX_VALUE` if not set
)
```
