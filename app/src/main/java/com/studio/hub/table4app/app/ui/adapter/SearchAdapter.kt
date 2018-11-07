package com.studio.hub.table4app.app.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.studio.hub.table4app.app.ui.fragment.NearMeFragment
import com.studio.hub.table4app.app.ui.fragment.RecommendedFragment
import com.studio.hub.table4app.app.ui.fragment.SearchFragment

class SearchAdapter(fragmentManager: FragmentManager) : SmartFragmentStatePagerAdapter(fragmentManager){
    override fun getItem(position: Int): Fragment {
        var fragment = Fragment()
        when(position){
            0 -> {
                fragment = NearMeFragment.newInstance(" Hi param1" , " Hu param2")
            }
            1 -> {
                fragment = RecommendedFragment.newInstance(" Hi param1" , " Hu param2")
            }
        }
        return fragment
    }

    override fun getCount(): Int = 2
}