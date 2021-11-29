/*
 *
 *  Copyright 2021 Orange
 *
 *  Use of this source code is governed by an MIT-style
 *  license that can be found in the LICENSE file or at
 *  https://opensource.org/licenses/MIT.
 * /
 */

package com.orange.ods.sample.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import com.orange.ods.sample.databinding.DemoItemBinding
import com.orange.ods.sample.tools.list.DataBoundListAdapter

internal class DemoListAdapter(private val clickCallback: ((Demo) -> Unit)? = null) :
    DataBoundListAdapter<Demo, DemoItemBinding>(DIFF_CALLBACK) {

    init {
        setHasStableIds(true)
    }

    override fun getItemId(position: Int): Long {
        val item = getItem(position)
        return item.ordinal.toLong()
    }

    override fun createBinding(parent: ViewGroup, viewType: Int): DemoItemBinding =
        DemoItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        ).apply {
            root.setOnClickListener { demo?.let { clickCallback?.invoke(it) } }
        }

    override fun bind(binding: DemoItemBinding, item: Demo) {
        binding.demo = item
    }

    companion object {
        private val DIFF_CALLBACK = object :
            DiffUtil.ItemCallback<Demo>() {

            override fun areItemsTheSame(
                old: Demo,
                new: Demo
            ) = old.ordinal == new.ordinal

            override fun areContentsTheSame(
                old: Demo,
                aNew: Demo
            ) = old == aNew
        }
    }


}
