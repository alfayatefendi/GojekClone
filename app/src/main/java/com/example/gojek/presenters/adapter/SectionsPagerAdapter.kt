package com.example.gojek.presenters.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.gojek.presenters.ui.fragment.chat.ChatFragment
import com.example.gojek.presenters.ui.fragment.home.HomeFragment
import com.example.gojek.presenters.ui.fragment.order.OrderFragment
import com.example.gojek.presenters.ui.fragment.promo.PromoFragment

class SectionsPagerAdapter (
    fragment: FragmentActivity
): FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 4

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = HomeFragment()
            1 -> fragment = PromoFragment()
            2 -> fragment = OrderFragment()
            3 -> fragment = ChatFragment()
        }
        return fragment as Fragment
    }
}