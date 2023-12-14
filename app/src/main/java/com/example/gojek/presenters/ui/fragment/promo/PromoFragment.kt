package com.example.gojek.presenters.ui.fragment.promo

import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import com.example.gojek.R
import com.example.gojek.data.DataSource
import com.example.gojek.databinding.FragmentPromoBinding
import com.example.gojek.presenters.adapter.ListPromoAdapter
import com.google.android.material.chip.Chip

class PromoFragment : Fragment() {

    private var _binding: FragmentPromoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPromoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dataset = DataSource.getDataPromo()
        binding.apply {
            rvPromo.setHasFixedSize(true)
            rvPromo.adapter = ListPromoAdapter(dataset)
        }
        addChip()
    }
    private fun addChip() {
        val dataTypePromo = resources.getStringArray(R.array.type_promo)
        dataTypePromo.forEachIndexed { index, type ->
            val chip = Chip(requireContext())
            chip.apply {
                text = type
                if (index == 0) {
                    chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(requireContext(), R.color.green))
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                } else{
                    setTextColor(ContextCompat.getColor(requireContext(), R.color.black))
                }
            }
            binding.cgTypePromo.addView(chip)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}