package com.bangkit.scrapncraft.ui.main.library

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.bangkit.scrapncraft.ui.main.library.draft.DraftFragment
import com.bangkit.scrapncraft.ui.main.library.saved.SavedFragment

class LibrarySectionPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = SavedFragment()
            1 -> fragment = DraftFragment()
        }
        return fragment as Fragment
    }
}