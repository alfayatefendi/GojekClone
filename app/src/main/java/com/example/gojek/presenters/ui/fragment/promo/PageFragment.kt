package com.example.gojek.presenters.ui.fragment.promo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.gojek.R
import com.example.gojek.databinding.FragmentPageBinding
import com.example.gojek.presenters.adapter.SectionsPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class PageFragment : Fragment() {

    private var _binding: FragmentPageBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPageBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tabTitle = requireActivity().resources.getStringArray(R.array.title_tab)
        val sectionsPagerAdapter = SectionsPagerAdapter(requireActivity())

        binding.apply {
            viewPager.adapter = sectionsPagerAdapter
            viewPager.isUserInputEnabled = false
        }
        TabLayoutMediator(binding.tabNav, binding.viewPager) { tab, position ->
            tab.text = tabTitle[position]
        }.attach()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}