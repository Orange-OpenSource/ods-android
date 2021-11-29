/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.sample.radio

import android.view.ViewGroup
import android.widget.RadioButton
import androidx.annotation.StringRes
import com.orange.ods.sample.BaseFragment
import com.orange.ods.sample.R
import com.orange.ods.sample.databinding.FragmentRadioButtonBinding
import com.orange.ods.sample.tools.*

class RadioButtonFragment :
    BaseFragment<FragmentRadioButtonBinding>(R.layout.fragment_radio_button) {

    private lateinit var enabled: RadioButton
    private lateinit var hovered: RadioButton
    private lateinit var focused: RadioButton
    private lateinit var pressed: RadioButton
    private lateinit var dragged: RadioButton
    private lateinit var disabled: RadioButton

    override fun setUpView() {
        super.setUpView()

        setUpRadioButtons()

        binding.checked.setOnCheckedChangeListener { _, _ ->
            setUpRadioButtons()
            setUpStates()
        }
    }

    override fun onResume() {
        super.onResume()

        setUpStates()
    }

    private fun setUpRadioButtons() {
        with(binding.container) {
            removeAllViews()

            enabled = addRadioButton(R.string.state_enabled)
            hovered = addRadioButton(R.string.state_hovered)
            focused = addRadioButton(R.string.state_focused)
            pressed = addRadioButton(R.string.state_pressed)
            dragged = addRadioButton(R.string.state_dragged)
            disabled = addRadioButton(R.string.state_disabled)
        }
    }

    private fun setUpStates() {
        enabled.enabledState()
        disabled.disabledState()
        pressed.pressedState()
        focused.focusedState()
        hovered.hoveredState()
        dragged.draggedState()
    }

    private fun ViewGroup.addRadioButton(@StringRes textResId: Int): RadioButton {

        return (layoutInflater.inflate(R.layout.radio_button, this, false) as RadioButton).apply {
            text = getString(textResId)
            isChecked = binding.checked.isChecked
        }.also {
            addView(it)
        }
    }

}