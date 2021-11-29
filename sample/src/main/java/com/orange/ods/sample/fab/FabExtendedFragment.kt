/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.sample.fab

import com.orange.ods.sample.BaseFragment
import com.orange.ods.sample.R
import com.orange.ods.sample.databinding.FragmentFabExtendedBinding
import com.orange.ods.sample.databinding.FragmentFabMiniBinding
import com.orange.ods.sample.tools.*

class FabExtendedFragment : BaseFragment<FragmentFabExtendedBinding>(R.layout.fragment_fab_extended) {

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