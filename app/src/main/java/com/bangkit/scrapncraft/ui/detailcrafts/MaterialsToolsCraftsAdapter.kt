package com.bangkit.scrapncraft.ui.detailcrafts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.scrapncraft.data.remote.response.DataDetailItem
import com.bangkit.scrapncraft.databinding.ItemMaterialsToolsBinding

class MaterialsToolsCraftsAdapter : ListAdapter<String, MaterialsToolsCraftsAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemMaterialsToolsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val materialTools = getItem(position)
        holder.bind(materialTools)
    }

    inner class MyViewHolder(val binding: ItemMaterialsToolsBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        fun bind(materialTools: String) {
            binding.tvCraftsMaterial.text = materialTools
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