package com.bangkit.scrapncraft.ui.main.explore

import android.Manifest
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.scrapncraft.data.remote.response.DataItem
import com.bangkit.scrapncraft.databinding.FragmentExploreBinding
import com.bangkit.scrapncraft.ui.main.MainViewModelFactory
import com.bangkit.scrapncraft.ui.main.explore.camera.CameraActivity
import com.bangkit.scrapncraft.ui.main.home.listcrafts.ListCraftsAdapter

class ExploreFragment : Fragment() {

    private var _binding: FragmentExploreBinding? = null
    private val binding get() = _binding!!
    private lateinit var exploreViewModel: ExploreViewModel

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(requireContext(), "Permission request granted", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(requireContext(), "Permission request denied", Toast.LENGTH_LONG).show()
            }
        }
    private fun allPermissionsGranted() =
        ContextCompat.checkSelfPermission(
            requireContext(),
            REQUIRED_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val factory: MainViewModelFactory = MainViewModelFactory.getInstance(requireActivity())
        exploreViewModel = ViewModelProvider(this, factory).get(ExploreViewModel::class.java)

        _binding = FragmentExploreBinding.inflate(inflater, container, false)
        val root: View = binding.root

        if (!allPermissionsGranted()) {
            requestPermissionLauncher.launch(REQUIRED_PERMISSION)
        }

        binding.fabAdd.setOnClickListener {
            val intent = Intent(requireContext(), CameraActivity::class.java)
            startActivity(intent)
        }

        val craftsAdapter = ListCraftsAdapter()
        binding?.rvCrafts?.apply {
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
            adapter = craftsAdapter
        }
        exploreViewModel.craftsList.observe(viewLifecycleOwner, { craftsList ->
            craftsAdapter.submitList(craftsList)
        })

        val filter = IntentFilter("UPDATE_SEARCH_ACTION")
        requireContext().registerReceiver(broadcastReceiver, filter)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(title: String?): Boolean {
                if (!title.isNullOrBlank()) {
                    exploreViewModel.searchTitleCrafts(title)
                }
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })

        binding.btnCategoryOrganic.setOnClickListener {
            val category = "organik"
            exploreViewModel.searchCategoryCrafts(category)
        }

        binding.btnCategoryNonOrganic.setOnClickListener {
            val category = "non-organik"
            exploreViewModel.searchCategoryCrafts(category)
        }

        return root
    }

    private val broadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            intent?.let {
                if (it.action == "UPDATE_SEARCH_ACTION") {
                    val tools = it.getStringExtra("HIGHEST_RESULT")
                    exploreViewModel.searchToolsCrafts(tools ?: "")
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val REQUIRED_PERMISSION = Manifest.permission.CAMERA
    }
}