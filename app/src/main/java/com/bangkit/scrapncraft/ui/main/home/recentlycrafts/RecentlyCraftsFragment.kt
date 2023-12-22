package com.bangkit.scrapncraft.ui.main.home.recentlycrafts

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bangkit.scrapncraft.data.remote.response.DataItem
import com.bangkit.scrapncraft.databinding.FragmentRecentlyCraftsBinding
import com.bangkit.scrapncraft.ui.addcrafts.AddCraftsActivity
import com.bangkit.scrapncraft.ui.main.MainViewModelFactory
import com.bangkit.scrapncraft.ui.main.home.listcrafts.ListCraftsAdapter
import com.bangkit.scrapncraft.ui.main.home.listcrafts.ListCraftsViewModel

class RecentlyCraftsFragment : Fragment() {
    private var _binding: FragmentRecentlyCraftsBinding? = null
    private val binding get() = _binding!!
    private lateinit var recycler: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecentlyCraftsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val factory: MainViewModelFactory = MainViewModelFactory.getInstance(requireActivity())
        val recentlyCraftsViewModel: RecentlyCraftsViewModel by viewModels {
            factory
        }

        val craftsAdapter = ListCraftsAdapter()

        recentlyCraftsViewModel.getRecentlyViewedCrafts().observe(viewLifecycleOwner, { recentlyViewedCrafts ->
            craftsAdapter.submitList(recentlyViewedCrafts)
        })

        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                return makeMovementFlags(0, ItemTouchHelper.RIGHT)
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val crafts = (viewHolder as ListCraftsAdapter.MyViewHolder).getCrafts
                recentlyCraftsViewModel.deleteOldRecentlyViewedCrafts(crafts.viewedTimestamp)
            }

        })
        itemTouchHelper.attachToRecyclerView(binding.rvRecentlyCrafts)

        binding?.rvRecentlyCrafts?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = craftsAdapter
        }

        binding.fabAdd.setOnClickListener {
            val intent = Intent(requireContext(), AddCraftsActivity::class.java)
            startActivity(intent)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}