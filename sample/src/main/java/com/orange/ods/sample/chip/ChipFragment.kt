package com.orange.ods.sample.chip

import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import com.google.android.material.chip.Chip
import com.orange.ods.sample.BaseFragment
import com.orange.ods.sample.R
import com.orange.ods.sample.databinding.FragmentChipBinding
import com.orange.ods.sample.tools.*

abstract class ChipFragment(
    @LayoutRes private val chipLayoutResId: Int,
    val addSelectedState: Boolean
) :
    BaseFragment<FragmentChipBinding>(R.layout.fragment_chip) {

    private lateinit var enabled: Chip
    private lateinit var hovered: Chip
    private lateinit var focused: Chip
    private lateinit var pressed: Chip
    private lateinit var dragged: Chip
    private lateinit var disabled: Chip

    private var selected: Chip? = null

    override fun setUpView() {
        super.setUpView()

        setUpChips()
    }

    override fun onResume() {
        super.onResume()

        setUpStates()
    }

    private fun setUpChips() {
        with(binding.chipContainer) {
            removeAllViews()

            enabled = addChip(R.string.state_enabled)
            hovered = addChip(R.string.state_hovered)
            focused = addChip(R.string.state_focused)
            pressed = addChip(R.string.state_pressed)
            dragged = addChip(R.string.state_dragged)

            if (addSelectedState) {
                selected = addChip(R.string.state_checked)
            }

            disabled = addChip(R.string.state_disabled)
        }
    }

    private fun setUpStates() {
        enabled.enabledState()
        disabled.disabledState()
        pressed.pressedState()
        focused.focusedState()
        hovered.hoveredState()
        dragged.draggedState()
        selected?.checkedState()
    }

    private fun ViewGroup.addChip(@StringRes textResId: Int): Chip {

        return (layoutInflater.inflate(chipLayoutResId, this, false) as Chip).apply {
            text = getString(textResId)
        }.also {
            addView(it)
        }
    }

}