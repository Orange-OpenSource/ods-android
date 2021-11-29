/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.sample.checkbox

import android.view.ViewGroup
import android.widget.CheckBox
import androidx.annotation.StringRes
import com.orange.ods.sample.BaseFragment
import com.orange.ods.sample.R
import com.orange.ods.sample.databinding.FragmentCheckboxBinding
import com.orange.ods.sample.tools.*

class CheckboxFragment :
    BaseFragment<FragmentCheckboxBinding>(R.layout.fragment_checkbox) {

    private lateinit var enabled: CheckBox
    private lateinit var hovered: CheckBox
    private lateinit var focused: CheckBox
    private lateinit var pressed: CheckBox
    private lateinit var dragged: CheckBox
    private lateinit var disabled: CheckBox

    override fun setUpView() {
        super.setUpView()

        setUpCheckboxes()

        binding.checked.setOnCheckedChangeListener { _, _ ->
            setUpCheckboxes()
            setUpStates()
        }
    }

    override fun onResume() {
        super.onResume()

        setUpStates()
    }

    private fun setUpCheckboxes() {
        with(binding.container) {
            removeAllViews()

            enabled = addCheckbox(R.string.state_enabled)
            hovered = addCheckbox(R.string.state_hovered)
            focused = addCheckbox(R.string.state_focused)
            pressed = addCheckbox(R.string.state_pressed)
            dragged = addCheckbox(R.string.state_dragged)
            disabled = addCheckbox(R.string.state_disabled)
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

    private fun ViewGroup.addCheckbox(@StringRes textResId: Int): CheckBox {

        return (layoutInflater.inflate(R.layout.checkbox, this, false) as CheckBox).apply {
            text = getString(textResId)
            isChecked = binding.checked.isChecked
        }.also {
            addView(it)
        }
    }

}