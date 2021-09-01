package com.orange.ods.sample.textfield

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import com.google.android.material.textfield.TextInputLayout
import com.orange.ods.sample.BaseFragment
import com.orange.ods.sample.R
import com.orange.ods.sample.databinding.FragmentTextfieldBinding
import com.orange.ods.sample.tools.*
import com.orange.ods.sample.tools.disabledState
import com.orange.ods.sample.tools.enabledState
import com.orange.ods.sample.tools.focusedState

abstract class TextFieldFragment(
    @LayoutRes private val textFieldLayoutResId: Int
) :
    BaseFragment<FragmentTextfieldBinding>(R.layout.fragment_textfield) {

    private lateinit var inactivated: TextInputLayout
    private lateinit var hovered: TextInputLayout
    private lateinit var focused: TextInputLayout
    private lateinit var activated: TextInputLayout
    private lateinit var error: TextInputLayout
    private lateinit var disabled: TextInputLayout

    override fun setUpView() {
        super.setUpView()

        setUpTextFields()
    }

    override fun onResume() {
        super.onResume()

        setUpStates()
    }

    private fun setUpTextFields() {
        with(binding.container) {
            removeAllViews()

            inactivated = addTextField(R.string.state_inactivated)
            focused = addTextField(R.string.state_focused).apply {
                editText?.setText(R.string.textfield_input_text)
            }
            hovered = addTextField(R.string.state_hovered).apply {
                editText?.setText(R.string.textfield_input_text)
            }
            activated = addTextField(R.string.state_activated).apply {
                editText?.setText(R.string.textfield_input_text)
            }
            error = addTextField(R.string.state_error).apply {
                editText?.setText(R.string.textfield_input_text)
                error = getText(R.string.textfield_input_error)
                isErrorEnabled = true
            }
            disabled = addTextField(R.string.state_disabled)
        }
    }

    private fun setUpStates() {
        inactivated.enabledState()
        activated.enabledState()
        error.enabledState()
        disabled.disabledState()
        focused.focusedState()
        hovered.hoveredState()
    }

    private fun ViewGroup.addTextField(@StringRes textResId: Int): TextInputLayout {

        return (layoutInflater.inflate(textFieldLayoutResId, this, false) as TextInputLayout).apply {
            helperText = getString(textResId)
        }.also {
            addView(it)
        }
    }

}