package com.orange.ods.sample.home

import androidx.navigation.fragment.findNavController
import com.orange.ods.sample.BaseFragment
import com.orange.ods.sample.R
import com.orange.ods.sample.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {

    override fun setUpView() {
        super.setUpView()

        binding.textButton.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToNavigationTextButton())
        }

        binding.outlinedButton.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToNavigationOutlinedButton())
        }

        binding.containedButton.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.actionNavigationHomeToNavigationContainedButton())
        }
    }
}