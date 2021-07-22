package com.orange.ods.sample.button

import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import com.orange.ods.sample.BaseFragment
import com.orange.ods.sample.R
import com.orange.ods.sample.databinding.FragmentButtonBinding
import com.orange.ods.sample.tools.*
import com.orange.ods.sample.tools.disabledState
import com.orange.ods.sample.tools.enabledState
import com.orange.ods.sample.tools.focusedState
import com.orange.ods.sample.tools.pressedState

abstract class ButtonFragment(
    @LayoutRes private val buttonLayoutResId: Int,
    @LayoutRes private val buttonWithIconLayoutResId: Int
) :
    BaseFragment<FragmentButtonBinding>(R.layout.fragment_button) {

    private lateinit var enabled: Button
    private lateinit var hovered: Button
    private lateinit var focused: Button
    private lateinit var pressed: Button
    private lateinit var dragged: Button
    private lateinit var disabled: Button

    override fun setUpView() {
        super.setUpView()

        setUpButtons()

        binding.showIcon.setOnCheckedChangeListener { _, _ ->
            setUpButtons()
            setUpButtonStates()
        }
    }

    override fun onResume() {
        super.onResume()

        setUpButtonStates()
    }

    private fun setUpButtons() {
        with(binding.buttonContainer) {
            removeAllViews()

            enabled = addButton(R.string.state_enabled)
            hovered = addButton(R.string.state_hovered)
            focused = addButton(R.string.state_focused)
            pressed = addButton(R.string.state_pressed)
            dragged = addButton(R.string.state_dragged)
            disabled = addButton(R.string.state_disabled)
        }
    }

    private fun setUpButtonStates() {
        enabled.enabledState()
        disabled.disabledState()
        pressed.pressedState()
        focused.focusedState()
        hovered.hoveredState()
        dragged.draggedState()
    }

    private fun ViewGroup.addButton(@StringRes textResId: Int): Button {

        val buttonLayoutResId = when (binding.showIcon.isChecked) {
            true -> buttonWithIconLayoutResId
            false -> buttonLayoutResId
        }

        return (layoutInflater.inflate(buttonLayoutResId, this, false) as Button).apply {
            text = getString(textResId)
        }.also {
            addView(it)
        }
    }

}