package com.orange.ods.sample.fab

import com.orange.ods.sample.BaseFragment
import com.orange.ods.sample.R
import com.orange.ods.sample.databinding.FragmentFabMiniBinding
import com.orange.ods.sample.tools.*

class FabMiniFragment : BaseFragment<FragmentFabMiniBinding>(R.layout.fragment_fab_mini) {

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