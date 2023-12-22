package com.bangkit.scrapncraft.ui.main.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bangkit.scrapncraft.ui.main.home.listcrafts.ListCraftsFragment
import com.bangkit.scrapncraft.ui.main.home.recentlycrafts.RecentlyCraftsFragment

class HomeSectionPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = ListCraftsFragment()
            1 -> fragment = RecentlyCraftsFragment()
        }
        return fragment as Fragment
    }

}