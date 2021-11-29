/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.sample.dialog

import android.system.Os.accept
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.orange.ods.sample.BaseFragment
import com.orange.ods.sample.R
import com.orange.ods.sample.databinding.FragmentDialogBinding
import com.orange.ods.sample.databinding.FragmentFabBinding
import com.orange.ods.sample.tools.*
import kotlinx.coroutines.NonCancellable.cancel

class DialogFragment : BaseFragment<FragmentDialogBinding>(R.layout.fragment_dialog) {

    override fun setUpView() {
        super.setUpView()

        binding.alert.setOnClickListener {

            MaterialAlertDialogBuilder(requireContext())
                .setTitle(resources.getString(R.string.dialog_layout_title))
                .setMessage(resources.getString(R.string.dialog_layout_message))
                .setNegativeButton(resources.getString(R.string.action_decline)) { _, _ -> }
                .setPositiveButton(resources.getString(R.string.action_accept)) { _, _ -> }
                .show()

        }

        binding.simple.setOnClickListener {

            MaterialAlertDialogBuilder(requireContext())
                .setTitle(resources.getString(R.string.dialog_layout_title))
                .setMessage(resources.getString(R.string.dialog_layout_long_message))
                .setNegativeButton(resources.getString(R.string.action_decline)) { _, _ -> }
                .setPositiveButton(resources.getString(R.string.action_accept)) { _, _ -> }
                .show()

        }

        binding.simpleWithIcon.setOnClickListener {

            MaterialAlertDialogBuilder(requireContext(), R.style.ThemeOverlay_MaterialComponents_MaterialAlertDialog_Centered)
                .setTitle(resources.getString(R.string.dialog_layout_title))
                .setIcon(R.drawable.ic_plus)
                .setMessage(resources.getString(R.string.dialog_layout_long_message))
                .setNegativeButton(resources.getString(R.string.action_decline)) { _, _ -> }
                .setPositiveButton(resources.getString(R.string.action_accept)) { _, _ -> }
                .show()

        }
    }


}