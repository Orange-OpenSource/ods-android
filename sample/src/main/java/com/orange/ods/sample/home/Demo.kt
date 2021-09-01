package com.orange.ods.sample.home

import androidx.annotation.StringRes
import com.orange.ods.sample.R

internal enum class Demo(@StringRes val nameResId: Int) {

    TEXT_BUTTON(R.string.demo_text_button),
    OUTLINED_BUTTON(R.string.demo_outlined_button),
    CONTAINED_BUTTON(R.string.demo_contained_button),
    TOGGLE_BUTTON(R.string.demo_toggle_button),
    TYPOGRAPHY(R.string.demo_typography),
    CHECKBOX(R.string.demo_checkbox),
    RADIO_BUTTON(R.string.demo_radio_button),
    SWITCH(R.string.demo_switch),
    FAB(R.string.demo_fab),
    FAB_MINI(R.string.demo_fab_mini),
    FAB_EXTENDED(R.string.demo_fab_extended),
    DIALOG(R.string.demo_dialog),
    TEXTFIELD_FILLED(R.string.demo_textfield_filled),
    TEXTFIELD_OUTLINED(R.string.demo_textfield_outlined),
    TAB(R.string.demo_tab),
    PROGRESS(R.string.demo_progress),
    SNACKBAR(R.string.demo_snackbar),
    CARD(R.string.demo_card),
    BOTTOM_NAVIGATION(R.string.demo_bottom_navigation),
    BOTTOM_APP_BAR(R.string.demo_bottom_app_bar),
    TOP_APP_BAR(R.string.demo_top_app_bar),
    SLIDER(R.string.demo_slider),
    INPUT_CHIP(R.string.demo_input_chip),
    FILTER_CHIP(R.string.demo_filter_chip),
    CHOICE_CHIP(R.string.demo_choice_chip),
    ACTION_CHIP(R.string.demo_action_chip),
}