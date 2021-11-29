/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.sample.switch

import android.view.ViewGroup
import android.widget.RadioButton
import android.widget.Switch
import androidx.annotation.StringRes
import androidx.appcompat.widget.SwitchCompat
import com.orange.ods.sample.BaseFragment
import com.orange.ods.sample.R
import com.orange.ods.sample.databinding.FragmentSwitchBinding
import com.orange.ods.sample.tools.*

class SwitchFragment :
    BaseFragment<FragmentSwitchBinding>(R.layout.fragment_switch) {

    private lateinit var enabled: SwitchCompat
    private lateinit var hovered: SwitchCompat
    private lateinit var focused: SwitchCompat
    private lateinit var pressed: SwitchCompat
    private lateinit var dragged: SwitchCompat
    private lateinit var disabled: SwitchCompat

    override fun setUpView() {
        super.setUpView()

        setUpSwitches()

        binding.checked.setOnCheckedChangeListener { _, _ ->
            setUpSwitches()
            setUpStates()
        }
    }

    override fun onResume() {
        super.onResume()

        setUpStates()
    }

    private fun setUpSwitches() {
        with(binding.container) {
            removeAllViews()

            enabled = addSwitch(R.string.state_enabled)
            hovered = addSwitch(R.string.state_hovered)
            focused = addSwitch(R.string.state_focused)
            pressed = addSwitch(R.string.state_pressed)
            dragged = addSwitch(R.string.state_dragged)
            disabled = addSwitch(R.string.state_disabled)
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

    private fun ViewGroup.addSwitch(@StringRes textResId: Int): SwitchCompat {

        return (layoutInflater.inflate(R.layout.switch_material, this, false) as SwitchCompat).apply {
            text = getString(textResId)
            isChecked = binding.checked.isChecked
        }.also {
            addView(it)
        }
    }

}