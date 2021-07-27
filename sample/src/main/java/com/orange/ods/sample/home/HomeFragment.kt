package com.orange.ods.sample.home

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.orange.ods.sample.BaseFragment
import com.orange.ods.sample.R
import com.orange.ods.sample.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    companion object {
        private val DEMO_ACTIONS = mapOf(
            Demo.TEXT_BUTTON to HomeFragmentDirections.actionNavigationHomeToNavigationTextButton(),
            Demo.CONTAINED_BUTTON to HomeFragmentDirections.actionNavigationHomeToNavigationContainedButton(),
            Demo.OUTLINED_BUTTON to HomeFragmentDirections.actionNavigationHomeToNavigationOutlinedButton(),
            Demo.TYPOGRAPHY to HomeFragmentDirections.actionNavigationHomeToNavigationTypography(),
            Demo.CHECKBOX to HomeFragmentDirections.actionNavigationHomeToNavigationCheckbox(),
            Demo.RADIO_BUTTON to HomeFragmentDirections.actionNavigationHomeToNavigationRadioButton(),
            Demo.SWITCH to HomeFragmentDirections.actionNavigationHomeToNavigationSwitch(),
            Demo.FAB to HomeFragmentDirections.actionNavigationHomeToNavigationFab(),
            Demo.FAB_MINI to HomeFragmentDirections.actionNavigationHomeToNavigationFabMini(),
            Demo.FAB_EXTENDED to HomeFragmentDirections.actionNavigationHomeToNavigationFabExtended(),
            Demo.DIALOG to HomeFragmentDirections.actionNavigationHomeToNavigationDialog(),
            Demo.FORM to HomeFragmentDirections.actionNavigationHomeToNavigationForm(),
            Demo.TAB to HomeFragmentDirections.actionNavigationHomeToNavigationTab(),
            Demo.PROGRESS to HomeFragmentDirections.actionNavigationHomeToNavigationProgress(),
        )
    }

    private val adapter = DemoListAdapter(clickCallback = {
        DEMO_ACTIONS[it]?.let {
            findNavController().navigate(it)
        }
    }).apply {
        submitList(Demo.values().toList())
    }


    override fun setUpView() {
        super.setUpView()

        with(binding.demo) {
            setHasFixedSize(true)
            adapter = this@HomeFragment.adapter

            val divider = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
            addItemDecoration(divider)
        }
    }
}