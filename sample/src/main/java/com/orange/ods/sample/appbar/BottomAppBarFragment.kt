package com.orange.ods.sample.appbar

import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.button.MaterialButton
import com.orange.ods.sample.BaseFragment
import com.orange.ods.sample.R
import com.orange.ods.sample.databinding.FragmentBottomAppBarBinding


class BottomAppBarFragment :
    BaseFragment<FragmentBottomAppBarBinding>(R.layout.fragment_bottom_app_bar) {

    override fun setUpView() {
        super.setUpView()

        binding.fabHide.setOnClickListener { binding.fab.hide() }

        binding.fabPositionButtonCenter.setOnClickListener {
            binding.fab.show()
            binding.bar.setFabAlignmentModeAndReplaceMenu(
                BottomAppBar.FAB_ALIGNMENT_MODE_CENTER,
                R.menu.menu_main
            )
        }
        (binding.fabPositionButtonCenter as? MaterialButton)?.isChecked = true

        binding.fabPositionButtonEnd.setOnClickListener {
            binding.fab.show()
            binding.bar.setFabAlignmentModeAndReplaceMenu(
                BottomAppBar.FAB_ALIGNMENT_MODE_END,
                R.menu.menu_main
            )
        }
    }


}