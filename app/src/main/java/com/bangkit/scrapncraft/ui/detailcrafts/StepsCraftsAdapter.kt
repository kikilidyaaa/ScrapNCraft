package com.bangkit.scrapncraft.ui.detailcrafts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.scrapncraft.data.remote.response.DataDetailItem
import com.bangkit.scrapncraft.databinding.ItemStepsBinding

class StepsCraftsAdapter : ListAdapter<String, StepsCraftsAdapter.MyViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemStepsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val steps = getItem(position)
        holder.bind(steps)
    }

    inner class MyViewHolder(val binding: ItemStepsBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(steps: String) {
            binding.tvCraftsStep.text = steps
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<String> =
            object : DiffUtil.ItemCallback<String>() {
                override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                    return oldItem == newItem
                }

                override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                    return oldItem == newItem
                }
            }
    }
}