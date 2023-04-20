package com.darth.unitconverter.view

import ChildBasic01Fragment
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.darth.unitconverter.R
import com.darth.unitconverter.adapter.ViewPagerAdapter
import com.darth.unitconverter.databinding.FragmentLivingBinding
import com.google.android.material.tabs.TabLayoutMediator


class ParentLivingFragment : Fragment() {

    private var _binding: FragmentLivingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLivingBinding.inflate(inflater, container, false)
        addFragment()
        return binding.root
    }

    private fun addFragment() {
        val tabLayout2 = binding.tabLayoutParentLivingId
        val viewPager2 = binding.viewPagerParentLivingId

        val adapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        adapter.addFragment(ChildLiving01Fragment(), "Man Shoes")
        adapter.addFragment(ChildBasic01Fragment(), "Length")
        adapter.addFragment(ChildBasic02Fragment(), "Weight")
        viewPager2.adapter = adapter

        TabLayoutMediator(tabLayout2, viewPager2) { tab, position ->
            tab.text = adapter.getPageTitle(position)
        }.attach()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
