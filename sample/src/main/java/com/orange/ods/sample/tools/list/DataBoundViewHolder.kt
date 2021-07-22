package com.orange.ods.sample.tools.list

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class DataBoundViewHolder<out T : ViewDataBinding> internal constructor(val binding: T) :
    RecyclerView.ViewHolder(binding.root)