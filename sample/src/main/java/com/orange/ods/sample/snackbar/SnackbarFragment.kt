package com.orange.ods.sample.snackbar

import com.google.android.material.snackbar.Snackbar
import com.orange.ods.sample.BaseFragment
import com.orange.ods.sample.R
import com.orange.ods.sample.databinding.FragmentSnackbarBinding

class SnackbarFragment : BaseFragment<FragmentSnackbarBinding>(R.layout.fragment_snackbar) {

    override fun setUpView() {
        super.setUpView()

        binding.singleLine.setOnClickListener {
            Snackbar.make(binding.root, R.string.snackbar_one_line, Snackbar.LENGTH_LONG)
                .show()
        }

        binding.singleLineWithButton.setOnClickListener {
            Snackbar.make(binding.root, R.string.snackbar_one_line_with_action, Snackbar.LENGTH_LONG)
                .setAction(R.string.action_accept) {}
                .show()
        }

        binding.multiLine.setOnClickListener {
            Snackbar.make(binding.root, R.string.snackbar_two_line, Snackbar.LENGTH_LONG)
                .show()
        }

        binding.multiLineWithButton.setOnClickListener {
            Snackbar.make(binding.root, R.string.snackbar_two_line_with_action, Snackbar.LENGTH_LONG)
                .setAction(R.string.action_accept) {}
                .show()
        }

    }


}