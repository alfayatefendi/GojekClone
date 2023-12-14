package com.example.gojek.presenters.ui.fragment.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gojek.R
import com.example.gojek.data.DataSource
import com.example.gojek.databinding.FragmentHomeBinding
import com.example.gojek.presenters.adapter.ListNewsAdapter

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val dataset = DataSource.getDataNews()
        binding.rvNews.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ListNewsAdapter(dataset)
            binding.frameGopay.btnPay.setOnClickListener { goToPay() }
        }
    }
    private fun goToPay(){
        findNavController().navigate(R.id.action_pageFragment_to_payFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}