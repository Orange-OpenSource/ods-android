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

#### Jetpack Compose implementation

To add a text field in your composable screen you can use the `OdsTextField` composable as follow:

```kotlin
var text by rememberSaveable { mutableStateOf("") }
OdsTextField(
    leadingIcon = painterResource(id = R.drawable.ic_heart),
    leadingIconContentDescription = "Like",
    onLeadingIconClick = { doSomething() },
    trailing = OdsTextTrailing(text = "units"), // It can be one of the provided `OdsTextFieldTrailing`. See more information below.
    enabled = true,
    readOnly = false,
    isError = false,
    errorMessage = "Error message",
    value = text,
    onValueChange = { text = it },
    label = "Label",
    placeholder = "Placeholder",
    visualTransformation = VisualTransformation.None,
    keyboardOptions = KeyboardOptions.Default,
    keyboardActions = KeyboardActions(),
    singleLine = false,
    maxLines = Int.MAX_VALUE,
    characterCounter = {
        OdsTextFieldCharacterCounter(
            valueLength = valueLength,
            maxChars = TextFieldMaxChars,
            enabled = enabled
        )
    }
)
```

The library provides several `OdsTextFieldTrailing` that you can use as a trailing element for text field:

- `OdsIconTrailing`: Displays an icon as trailing element
- `OdsTextTrailing`: Displays a text as trailing element

If you want a more complex trailing element, you can use the other `OdsTextField` API which allows you to pass directly a composable as trailing but you will have to maintain it and to manage the accessibility by yourself.

**Note:** You will find more information about the character counter in [Extras](#extras)

**Custom theme configuration:
** You can override the default display of text fields in your custom theme by overriding the `textFieldStyle` attribute as below:

```kotlin
override val components: OdsComponentsConfiguration
    get() = object : OdsComponentsConfiguration() {
        override val textFieldStyle: ComponentStyle
            get() = ComponentStyle.Filled
    }
```

### Password text field

Password text field is a text field implementation that includes password visual transformation and optional visualisation icon.

![TextField outlined password light](images/textfield_outlined_password_light.png)  
![TextField outlined password dark](images/textfield_outlined_password_dark.png)

![TextField filled password light](images/textfield_filled_password_light.png)  
![TextField filled password dark](images/textfield_filled_password_dark.png)

#### Jetpack Compose implementation

To add a password text field in your composable screen you can use the `OdsPasswordTextField` composable as follow:

```kotlin
var text by rememberSaveable { mutableStateOf("") }
OdsPasswordTextField(
    enabled = true,
    readOnly = false,
    isError = false,
    errorMessage = "Error message",
    value = text,
    onValueChange = { text = it },
    label = "Label",
    placeholder = "Placeholder",
    visualisationIcon = true,
    keyboardOptions = KeyboardOptions.Default,
    keyboardActions = KeyboardActions(),
    characterCounter = {
        OdsTextFieldCharacterCounter(
            valueLength = valueLength,
            maxChars = TextFieldMaxChars,
            enabled = enabled
        )
    }
)
```

**Note:** This composable relies on `OdsTextField` in order to keep homogeneity in each application.
Its appearance (outlined or filled) is inherited from text fields style configuration.
See [text field section](#text-field) if you want to change it in your custom theme.

## Extras

### Character counter

![TextField character counter light](images/textfield_character_counter_light.png)  
![TextField character counter dark](images/textfield_character_counter_dark.png)

#### Jetpack Compose implementation

In each TextField component, you can use the `characterCounter` parameter to add a character counter if there is a restriction on the number of characters in a field.  
It will be placed properly below the text field, end aligned.

Please use the provided `OdsTextFieldCharacterCounter` composable for this behavior as shown below:

```kotlin
OdsTextFieldCharacterCounter(
    modifier = Modifier.align(Alignment.End),
    valueLength = valueLength,
    maxChars = 20,
    enabled = true // If `false` the counter is displayed with a disabled color
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
