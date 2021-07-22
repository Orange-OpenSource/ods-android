package com.orange.ods.sample.home

import androidx.annotation.StringRes
import com.orange.ods.sample.R

internal enum class Demo(@StringRes val nameResId: Int) {

    TEXT_BUTTON(R.string.demo_text_button),
    OUTLINED_BUTTON(R.string.demo_outlined_button),
    CONTAINED_BUTTON(R.string.demo_contained_button),
    TYPOGRAPHY(R.string.demo_typography),
    CHECKBOX(R.string.demo_checkbox),
    RADIO_BUTTON(R.string.demo_radio_button),
}