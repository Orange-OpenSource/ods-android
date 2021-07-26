package com.orange.ods.sample.fab

import com.orange.ods.sample.BaseFragment
import com.orange.ods.sample.R
import com.orange.ods.sample.databinding.FragmentFabBinding
import com.orange.ods.sample.tools.*

class FabFragment : BaseFragment<FragmentFabBinding>(R.layout.fragment_fab) {

    override fun onResume() {
        super.onResume()

        setUpButtonStates()
    }

    private fun setUpButtonStates() {
        with(binding) {
            enabled.enabledState()
            disabled.disabledState()
            pressed.pressedState()
            focused.focusedState()
            hovered.hoveredState()
        }
    }
}