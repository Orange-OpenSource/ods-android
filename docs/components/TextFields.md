---
layout: detail
title: Text fields
description: Text fields let users enter and edit text.
---

---

**Page Summary**

* [Specifications references](#specifications-references)
* [Accessibility](#accessibility)
* [Variants](#variants)
  * [Text field](#text-field)
  * [Password text field](#password-text-field)
* [Extras](#extras)
  * [Character counter](#character-counter)
* [Component specific tokens](#component-specific-tokens)

---

## Specifications references

- [Design System Manager - Text fields](https://system.design.orange.com/0c1af118d/p/483f94-text-fields/b/720e3b)
- [Material Design - Text fields](https://material.io/components/text-fields/)
- Technical documentation soon available

## Accessibility

Please follow [accessibility criteria for development](https://a11y-guidelines.orange.com/en/mobile/android/development/)

Android's text field component APIs support both label text and helper text for informing the user
as to what information is requested for a text field. While optional, their use is strongly
encouraged.

**Content description**

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

**Custom `EditText`**

If you are using a custom `EditText` as `TextInputLayout`'s child and your text field requires
different accessibility support than the one offered by
`TextInputLayout`, you can set a `TextInputLayout.AccessibilityDelegate` via the
`setTextInputAccessibilityDelegate` method. This method should be used in place of providing
an `AccessibilityDelegate` directly on the `EditText`.

## Variants

### Text field

A text field can be filled or outlined.  
The outlined version is more accessible in term of contrast. This is the reason why Orange text fields are outlined.

  ![TextField outlined light](images/textfield_outlined_light.png)  
  ![TextField outlined dark](images/textfield_outlined_dark.png)

  ![TextField filled light](images/textfield_filled_light.png)  
  ![TextField filled dark](images/textfield_filled_dark.png)

> **Jetpack Compose implementation**

To add a text field in your composable screen you can use the `OdsTextField` composable as follow:

```kotlin
var text by rememberSaveable { mutableStateOf("") }
OdsTextField(
    leadingIcon = painterResource(id = R.drawable.ic_heart), // Optional
    leadingIconContentDescription = "Like", // Optional
    onLeadingIconClick = { doSomething() }, // Optional
    enabled = true, // true if not set
    readOnly = false, // false if not set
    isError = false, // false if not set
    errorMessage = "Error message", // Optional
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
    characterCounter = {
        OdsTextFieldCharacterCounter(
            valueLength = valueLength,
            maxChars = TextFieldMaxChars,
            enabled = enabled
        )    
    } // Optional
)
```

**Note:** You will find more information about the character counter in [Extras](#extras)

**Custom theme configuration:** You can override the default display of text fields in your custom theme by overriding the `textFieldStyle` attribute as below:

```kotlin
override val components: OdsComponentsConfiguration
    get() = object : OdsComponentsConfiguration() {
        override val textFieldStyle: ComponentStyle
            get() = ComponentStyle.Filled
    }
```

> **XML implementation**

_**Note:** The filled text field is the default style if the style is not set._

API and source code:

* `TextInputLayout`: [Class definition](https://developer.android.com/reference/com/google/android/material/textfield/TextInputLayout), [Class source](https://github.com/material-components/material-components-android/tree/master/lib/java/com/google/android/material/textfield/TextInputLayout.java)
* `TextInputEditText`: [Class definition](https://developer.android.com/reference/com/google/android/material/textfield/TextInputEditText), [Class source](https://github.com/material-components/material-components-android/tree/master/lib/java/com/google/android/material/textfield/TextInputEditText.java)

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

For outlined version:

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

```kotlin
// Get input text
val inputText = textField.editText?.text.toString()

textField.editText?.doOnTextChanged { inputText, _, _, _ ->
    // Respond to input text change
}
```

### Password text field

Password text field is a text field implementation that includes password visual transformation and optional visualisation icon.

  ![TextField outlined password light](images/textfield_outlined_password_light.png)  
  ![TextField outlined password dark](images/textfield_outlined_password_dark.png)

  ![TextField filled password light](images/textfield_filled_password_light.png)  
  ![TextField filled password dark](images/textfield_filled_password_dark.png)

> **Jetpack Compose implementation**

To add a password text field in your composable screen you can use the `OdsPasswordTextField` composable as follow:

```kotlin
var text by rememberSaveable { mutableStateOf("") }
OdsPasswordTextField(
    enabled = true, // true if not set
    readOnly = false, // false if not set
    isError = false, // false if not set
    errorMessage = "Error message", // Optional
    value = text,
    onValueChange = { text = it },
    label = "Label", // Optional
    placeholder = "Placeholder", // Optional
    visualisationIcon = true, // `true` if not set
    keyboardOptions = KeyboardOptions.Default, // `KeyboardOptions.Default` if not set
    keyboardActions = KeyboardActions(), // `KeyboardActions()` if not set
    characterCounter = {
        OdsTextFieldCharacterCounter(
            valueLength = valueLength,
            maxChars = TextFieldMaxChars,
            enabled = enabled
        )    
    } // Optional
)
```

**Note:** This composable relies on `OdsTextField` in order to keep homogeneity in each application.
Its appearance (outlined or filled) is inherited from text fields style configuration.
See [text field section](#text-field) if you want to change it in your custom theme.

> **XML implementation**

_Not available_


## Extras

### Character counter

  ![TextField character counter light](images/textfield_character_counter_light.png)  
  ![TextField character counter dark](images/textfield_character_counter_dark.png)

> **Jetpack Compose implementation**

In each TextField component, you can use the `characterCounter` parameter to add a character counter if there is a restriction on the number of characters in a field.  
It will be placed properly below the text field, end aligned.

Please use the provided `OdsTextFieldCharacterCounter` composable for this behavior as shown below:

```kotlin
OdsTextFieldCharacterCounter(
    modifier = Modifier.align(Alignment.End),
    valueLength = valueLength, 
    maxChars = 20,
    enabled = true // `true` if not set. If `false` the counter is displayed with a disabled color
)
```

Be careful, the limitation behavior should be managed by yourself in the `onValueChange` method of the text field:

```kotlin
if (text.length <= TextFieldMaxChars) {
    value = text
}
```

## Component specific tokens

_Soon available_
