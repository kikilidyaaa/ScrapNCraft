package com.bangkit.scrapncraft.ui.addcrafts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.bangkit.scrapncraft.R
import com.bangkit.scrapncraft.databinding.ActivityAddCraftsBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class AddCraftsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddCraftsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddCraftsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddTools.setOnClickListener { addNewViewMaterialsTools() }
        binding.btnAddSteps.setOnClickListener { addNewViewSteps() }

        binding.btnDelete.setOnClickListener { }
    }

    private fun addNewViewMaterialsTools() {
        val inflater = LayoutInflater.from(this).inflate(R.layout.row_add_materials_tools, null)
        binding.linearLayoutMaterialsTools.addView(inflater, binding.linearLayoutMaterialsTools.childCount)
    }

    private fun addNewViewSteps() {
        val inflater = LayoutInflater.from(this).inflate(R.layout.row_add_steps, null)
        binding.linearLayoutSteps.addView(inflater, binding.linearLayoutSteps.childCount)
    }

    private fun removeViewMaterialsTools(view: View) {
        binding.linearLayoutMaterialsTools.removeView(view)
    }
}