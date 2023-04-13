package com.darth.unitconverter.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.darth.unitconverter.adapter.ViewPagerAdapter
import com.darth.unitconverter.databinding.FragmentParentBasicBinding
import com.google.android.material.tabs.TabLayoutMediator

class ParentBasicFragment : Fragment() {

    private var _binding: FragmentParentBasicBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentParentBasicBinding.inflate(inflater, container, false)
        addFragment()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


    private fun addFragment() {
        val tabLayout2 = binding.tabLayoutParentBasicId
        val viewPager2 = binding.viewPagerParentBasicId

        val adapter = ViewPagerAdapter(childFragmentManager, lifecycle)
        adapter.addFragment(ChildBasic01Fragment(), "Length")
        adapter.addFragment(ChildBasic02Fragment(), "Area")
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
