package com.bangkit.scrapncraft.ui.main.home.listcrafts

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.scrapncraft.databinding.FragmentListCraftsBinding
import com.bangkit.scrapncraft.ui.addcrafts.AddCraftsActivity
import com.bangkit.scrapncraft.ui.main.MainViewModelFactory
import com.bangkit.scrapncraft.utils.Result

class ListCraftsFragment : Fragment() {
    private var _binding: FragmentListCraftsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListCraftsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val factory: MainViewModelFactory = MainViewModelFactory.getInstance(requireActivity())
        val listCraftsViewModel: ListCraftsViewModel by viewModels {
            factory
        }

        val craftsAdapter = ListCraftsAdapter()

        listCraftsViewModel.getCrafts().observe(viewLifecycleOwner) { result ->
            if (result != null) {
                when (result) {
                    is Result.Loading -> {
                        binding?.progressBar?.visibility = View.VISIBLE
                    }
                    is Result.Success -> {
                        binding?.progressBar?.visibility = View.GONE
                        val craftsData = result.data
                        craftsAdapter.submitList(craftsData)
                    }
                    is Result.Error -> {
                        binding?.progressBar?.visibility = View.GONE
                        Toast.makeText(
                            context,
                            "Terjadi kesalahan" + result.error,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        }

        binding?.rvCrafts?.apply {
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