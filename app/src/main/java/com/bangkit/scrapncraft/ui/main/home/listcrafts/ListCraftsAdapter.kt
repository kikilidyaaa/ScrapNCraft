package com.bangkit.scrapncraft.ui.main.home.listcrafts

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.scrapncraft.data.remote.response.DataItem
import com.bangkit.scrapncraft.databinding.ItemCraftsBinding
import com.bangkit.scrapncraft.ui.main.home.listcrafts.ListCraftsAdapter.MyViewHolder
import com.bangkit.scrapncraft.ui.detailcrafts.DetailCraftsActivity

class ListCraftsAdapter : ListAdapter<DataItem, MyViewHolder>(DIFF_CALLBACK) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemCraftsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val crafts = getItem(position)
        holder.bind(crafts)
    }

    inner class MyViewHolder(val binding: ItemCraftsBinding) : RecyclerView.ViewHolder(
        binding.root
    ) {
        lateinit var getCrafts: DataItem
        fun bind(crafts: DataItem) {
            binding.itemTitle.text = crafts.title
            binding.itemDesc.text = crafts.desc
            binding.itemCategory.text = crafts.category

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailCraftsActivity::class.java)
                intent.putExtra("Title", crafts.title)
                intent.putExtra("Desc", crafts.desc)
                intent.putExtra("Category", crafts.category)


                Toast.makeText(itemView.context, crafts.title + " dipilih", Toast.LENGTH_SHORT).show()
                itemView.context.startActivity(intent)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<DataItem> =
            object : DiffUtil.ItemCallback<DataItem>() {
                override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                    return oldItem.title == newItem.title
                }

                override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                    return oldItem == newItem
                }
            }
    }
}