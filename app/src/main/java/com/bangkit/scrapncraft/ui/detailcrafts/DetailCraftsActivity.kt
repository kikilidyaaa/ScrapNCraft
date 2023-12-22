package com.bangkit.scrapncraft.ui.detailcrafts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bangkit.scrapncraft.data.remote.retrofit.ApiConfig
import com.bangkit.scrapncraft.data.remote.retrofit.ApiService
import com.bangkit.scrapncraft.databinding.ActivityDetailCraftsBinding
import com.bangkit.scrapncraft.ui.main.MainViewModelFactory

class DetailCraftsActivity : AppCompatActivity() {
    private lateinit var apiService: ApiService
    private lateinit var binding: ActivityDetailCraftsBinding
    private val detailCraftsViewModel by viewModels<DetailCraftsViewModel> {
        MainViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailCraftsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val title = intent.getStringExtra("Title") ?: ""

        val materialsToolsAdapter = MaterialsToolsCraftsAdapter()
        val stepsAdapter = StepsCraftsAdapter()

        detailCraftsViewModel.craftsDetail.observe(this, Observer { craftDetail ->
            binding.tvCraftsTitle.text = craftDetail.title
            binding.tvCraftsDesc.text = craftDetail.desc

            val stepsList: List<String> = craftDetail.steps?.split(", ") ?: emptyList()
            val materialsList: List<String> = craftDetail.tools?.split(", ") ?: emptyList()

            with(binding.rvMaterialsTools) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = materialsToolsAdapter
            }

            with(binding.rvSteps) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = stepsAdapter
            }

            materialsToolsAdapter.submitList(materialsList)
            stepsAdapter.submitList(stepsList)
        })

        detailCraftsViewModel.tools.observe(this, Observer { toolsList ->
            materialsToolsAdapter.submitList(toolsList)
        })

        detailCraftsViewModel.steps.observe(this, Observer { stepsList ->
            stepsAdapter.submitList(stepsList)
        })

        detailCraftsViewModel.getDetailCrafts(title)

    }
}