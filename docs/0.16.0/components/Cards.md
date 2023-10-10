---
layout: detail
title: Cards
description: Cards contain content and actions about a single subject.
---

---

**Page Summary**

* [Specifications references](#specifications-references)
* [Accessibility](#accessibility)
* [Variants](#variants)
    * [Vertical image first card](#vertical-image-first-card)
    * [Vertical header first card](#vertical-header-first-card)
    * [Small card](#small-card)
    * [Horizontal card](#horizontal-card)
* [Component specific tokens](#component-specific-tokens)

---

## Specifications references

- [Design System Manager - Cards](https://system.design.orange.com/0c1af118d/p/272739-cards/b/991690)
- [Material Design - Cards](https://material.io/components/cards/)
- Technical documentation soon available

## Accessibility

Please follow [accessibility criteria for development](https://a11y-guidelines.orange.com/en/mobile/android/development/)

The contents within a card should follow their own accessibility guidelines, such as images having content descriptions set on them.

If you have a draggable card, you should set an
[`AccessibilityDelegate`](https://developer.android.com/reference/android/view/View.AccessibilityDelegate)
on it, so that the behavior can be accessible via screen readers such as TalkBack.  
See the [draggable card section](https://material.io/components/cards/android#making-a-card-draggable) section for more info.

## Variants

The library offers several Composables for Jetpack Compose implementation. In XML, the library is only styling `MaterialCardView`.

### Vertical image first card

This is a full width card containing elements arranged vertically with an image as first element.

  ![Vertical image first card light](images/card_vertical_image_first_light.png) ![Vertical image first card dark](images/card_vertical_image_first_dark.png)

> **Jetpack Compose implementation**

In your composable screen you can use `OdsVerticalImageFirstCard` composable:

```kotlin
OdsVerticalImageFirstCard(
    title = "Title",
    image = OdsCardImage(
        painterResource(R.drawable.picture),
        "Picture content description",
        Alignment.Center,
        ContentScale.Crop,
        Color(0xff1b1b1b) //Optional
    ),
    subtitle = "Subtitle", //Optional
    text = "Text", //Optional
    firstButton = OdsCardButton("First button") {}, //Optional
    secondButton = OdsCardButton("Second button") {}, //Optional
    onClick = {} //Optional
)
```

> **XML implementation**

See [Cards implementation in XML](#cards-implementation-in-xml)

### Vertical header first card

This is a full width card containing elements arranged vertically with a header (thumbnail, title & subtitle) as first element.

  ![Vertical header first card light](images/card_vertical_header_first_light.png) ![Vertical header first card dark](images/card_vertical_header_first_dark.png)

> **Jetpack Compose implementation**

In your composable screen you can use `OdsVerticalHeaderFirstCard` composable:

```kotlin
OdsVerticalHeaderFirstCard(
    title = "Title",
    image = OdsCardImage(
        painterResource(R.drawable.picture),
        "Picture content description",
        Alignment.Center,
        ContentScale.Crop,
        Color(0xff1b1b1b) //Optional
    ),
    thumbnail = OdsCardThumbnail(
        painterResource(R.drawable.thumbnail),
        "Thumbnail content description"
    ), //Optional
    subtitle = "Subtitle", //Optional
    text = "Text", //Optional
    firstButton = OdsCardButton("First button") {}, //Optional
    secondButton = OdsCardButton("Second button") {}, //Optional
    onClick = {} //Optional
)
```

> **XML implementation**

See [Cards implementation in XML](#cards-implementation-in-xml)

### Small card

This is a small card which takes the half screen width.

  ![CardSmall](images/card_small_light.png) ![CardSmall dark](images/card_small_dark.png)

> **Jetpack Compose implementation**

You can add an `OdsSmallCard`composable in your screen to add a small card:

```kotlin
Row(
    horizontalArrangement = Arrangement.spacedBy(16.dp),
) {
    OdsSmallCard(
        title = "Title",
        image = OdsCardImage(
            painterResource(R.drawable.picture),
            "Picture content description"
        ),
        modifier = Modifier.weight(0.5f),
        onClick = {}
    )
    OdsSmallCard(
        title = "Title",
        image = OdsCardImage(
            painterResource(R.drawable.picture),
            "Picture content description"
        ),
        modifier = Modifier.weight(0.5f),
        onClick = {}
    )
}
```

> **XML implementation**

See [Cards implementation in XML](#cards-implementation-in-xml)

### Horizontal card

This is a full screen width card with an image on the side. The image can be displayed on the left or on the right.

  ![Horizontal card light](images/card_horizontal_light.png) ![Horizontal card dark](images/card_horizontal_dark.png)

> **Jetpack Compose implementation**

In your screen you can use `OdsHorizontalCard` composable:

```kotlin
OdsHorizontalCard(
    title = "Title",
    image = OdsCardImage(
        painterResource(R.drawable.picture),
        "Picture content description",
        Alignment.Center,
        ContentScale.Crop,
        Color(0xff1b1b1b) //Optional
    ),
    subtitle = "Subtitle", //Optional
    text = "Text", //Optional
    firstButton = OdsCardButton("First button") {}, //Optional
    secondButton = OdsCardButton("Second button") {}, //Optional
    imagePosition = OdsHorizontalCardImagePosition.Start,  //Optional. Start by default.
    divider = false, // Optional. True by default.
    onClick = {} //Optional
)
```


> **XML implementation**

See [Cards implementation in XML](#cards-implementation-in-xml)

### Cards implementation in XML

To have a Card in your layout you must add `com.google.android.material.card.MaterialCardView` in your layout.

API and source code:

*   `MaterialCardView`: [Class definition](https://developer.android.com/reference/com/google/android/material/card/MaterialCardView), [Class source](https://github.com/material-components/material-components-android/tree/master/lib/java/com/google/android/material/card/MaterialCardView.java)

In the layout:

```xml
<com.google.android.material.card.MaterialCardView
    android:id="@+id/card"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="8dp">

    <LinearLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:orientation="vertical">

        <!-- Media -->
        <ImageView
            android:layout_width="match_parent"
            android:layout_height="194dp"
            app:srcCompat="@drawable/media"
            android:scaleType="centerCrop"
            android:contentDescription="@string/content_description_media"
            />

        <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical"
            android:padding="16dp">

            <!-- Title, secondary and supporting text -->
            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="@string/title"
                android:textAppearance="?attr/textAppearanceHeadline6"
                />
            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginTop="8dp"
                android:text="@string/secondary_text"
                android:textAppearance="?attr/textAppearanceBody2"
                android:textColor="?android:attr/textColorSecondary"
                />
            <TextView
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginTop="16dp"
                android:text="@string/supporting_text"
                android:textAppearance="?attr/textAppearanceBody2"
                android:textColor="?android:attr/textColorSecondary"
                />

        </LinearLayout>

        <!-- Buttons -->
        <LinearLayout
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_margin="8dp"
            android:orientation="horizontal">
            <com.google.android.material.button.MaterialButton
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_marginEnd="8dp"
                android:text="@string/action_1"
                style="@style/Widget.Orange.Button.Text"
                />
            <com.google.android.material.button.MaterialButton
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="@string/action_2"
                style="@style/Widget.Orange.Button.Text"
                />
        </LinearLayout>

    </LinearLayout>

</com.google.android.material.card.MaterialCardView>
```

## Component specific tokens

_Soon available_
