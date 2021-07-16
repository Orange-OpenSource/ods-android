package com.orange.ods.sample.button

import android.view.ViewGroup
import android.widget.Button
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import com.orange.ods.sample.BaseFragment
import com.orange.ods.sample.R
import com.orange.ods.sample.databinding.FragmentButtonBinding

abstract class ButtonFragment(
    @LayoutRes private val buttonLayoutResId: Int,
    @LayoutRes private val buttonWithIconLayoutResId: Int
) :
    BaseFragment<FragmentButtonBinding>(R.layout.fragment_button) {

    private lateinit var enabledButton: Button
    private lateinit var hoveredButton: Button
    private lateinit var focusedButton: Button
    private lateinit var pressedButton: Button
    private lateinit var draggedButton: Button
    private lateinit var disabledButton: Button

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

            enabledButton = addButton(R.string.button_state_enabled)
            hoveredButton = addButton(R.string.button_state_hovered)
            focusedButton = addButton(R.string.button_state_focused)
            pressedButton = addButton(R.string.button_state_pressed)
            draggedButton = addButton(R.string.button_state_dragged)
            disabledButton = addButton(R.string.button_state_disabled)
        }
    }

    private fun setUpButtonStates() {
        enabledButton.enabledState()
        disabledButton.disabledState()
        pressedButton.pressedState()
        focusedButton.focusedState()
        hoveredButton.hoveredState()
        draggedButton.draggedState()
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