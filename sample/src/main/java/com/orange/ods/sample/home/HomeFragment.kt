package com.orange.ods.sample.home

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.orange.ods.sample.BaseFragment
import com.orange.ods.sample.R
import com.orange.ods.sample.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    companion object {
        private val DEMO_ACTIONS = linkedMapOf(
            Demo.TEXT_BUTTON to HomeFragmentDirections.actionNavigationHomeToNavigationTextButton(),
            Demo.CONTAINED_BUTTON to HomeFragmentDirections.actionNavigationHomeToNavigationContainedButton(),
            Demo.OUTLINED_BUTTON to HomeFragmentDirections.actionNavigationHomeToNavigationOutlinedButton(),
            Demo.TOGGLE_BUTTON to HomeFragmentDirections.actionNavigationHomeToNavigationToggleButton(),
            Demo.TYPOGRAPHY to HomeFragmentDirections.actionNavigationHomeToNavigationTypography(),
            Demo.CHECKBOX to HomeFragmentDirections.actionNavigationHomeToNavigationCheckbox(),
            Demo.RADIO_BUTTON to HomeFragmentDirections.actionNavigationHomeToNavigationRadioButton(),
            Demo.SWITCH to HomeFragmentDirections.actionNavigationHomeToNavigationSwitch(),
            Demo.FAB to HomeFragmentDirections.actionNavigationHomeToNavigationFab(),
            Demo.FAB_MINI to HomeFragmentDirections.actionNavigationHomeToNavigationFabMini(),
            Demo.FAB_EXTENDED to HomeFragmentDirections.actionNavigationHomeToNavigationFabExtended(),
            Demo.DIALOG to HomeFragmentDirections.actionNavigationHomeToNavigationDialog(),
            Demo.TEXTFIELD_FILLED to HomeFragmentDirections.actionNavigationHomeToNavigationTextfieldFilled(),
            Demo.TEXTFIELD_OUTLINED to HomeFragmentDirections.actionNavigationHomeToNavigationTextfieldOutlined(),
            Demo.TAB to HomeFragmentDirections.actionNavigationHomeToNavigationTab(),
            Demo.BOTTOM_NAVIGATION to HomeFragmentDirections.actionNavigationHomeToNavigationBottomNavigation(),
            Demo.BOTTOM_APP_BAR to HomeFragmentDirections.actionNavigationHomeToNavigationBottomAppBar(),
            Demo.TOP_APP_BAR to HomeFragmentDirections.actionNavigationHomeToNavigationTopAppBar(),
            Demo.PROGRESS to HomeFragmentDirections.actionNavigationHomeToNavigationProgress(),
            Demo.SNACKBAR to HomeFragmentDirections.actionNavigationHomeToNavigationSnackbar(),
            Demo.CARD to HomeFragmentDirections.actionNavigationHomeToNavigationCard(),
            Demo.SLIDER to HomeFragmentDirections.actionNavigationHomeToNavigationSlider(),
            Demo.INPUT_CHIP to HomeFragmentDirections.actionNavigationHomeToNavigationInputChip(),
            Demo.FILTER_CHIP to HomeFragmentDirections.actionNavigationHomeToNavigationFilterChip(),
            Demo.CHOICE_CHIP to HomeFragmentDirections.actionNavigationHomeToNavigationChoiceChip(),
            Demo.ACTION_CHIP to HomeFragmentDirections.actionNavigationHomeToNavigationActionChip(),
        )
    }

    private val adapter = DemoListAdapter(clickCallback = {
        DEMO_ACTIONS[it]?.let {
            findNavController().navigate(it)
        }
    }).apply {
        submitList(DEMO_ACTIONS.keys.toList())
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