package com.orange.ods.sample.card

import android.system.Os.accept
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.orange.ods.sample.BaseFragment
import com.orange.ods.sample.R
import com.orange.ods.sample.databinding.FragmentCardBinding
import com.orange.ods.sample.databinding.FragmentDialogBinding
import com.orange.ods.sample.databinding.FragmentFabBinding
import com.orange.ods.sample.tools.*
import kotlinx.coroutines.NonCancellable.cancel

class CardFragment : BaseFragment<FragmentCardBinding>(R.layout.fragment_card) {

    override fun setUpView() {
        super.setUpView()

        binding.share.setOnClickListener {  }
    }

}