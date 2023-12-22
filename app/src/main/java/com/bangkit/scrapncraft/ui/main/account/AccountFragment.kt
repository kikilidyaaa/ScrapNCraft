package com.bangkit.scrapncraft.ui.main.account

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bangkit.scrapncraft.R
import com.bangkit.scrapncraft.databinding.FragmentAccountBinding
import com.bangkit.scrapncraft.ui.login.LoginActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth

class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        val root: View = binding.root

        auth = Firebase.auth
        val username = binding.itemUser
        val user = Firebase.auth.currentUser

        if (auth != null) {
            username.text = user!!.email
        } else {
            startActivity(Intent(activity, LoginActivity::class.java))
        }

        binding.signOut.setOnClickListener {
            Firebase.auth.signOut()
            startActivity(Intent(activity, LoginActivity::class.java))
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}