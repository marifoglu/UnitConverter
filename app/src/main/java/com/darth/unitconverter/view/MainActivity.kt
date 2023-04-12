package com.darth.unitconverter.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.darth.unitconverter.R
import com.darth.unitconverter.adapter.ViewPagerAdapter
import com.darth.unitconverter.databinding.ActivityMainBinding
import com.darth.unitconverter.databinding.FragmentParentBasicBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.tabs.TabLayoutMediator

private lateinit var binding : ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var content: FrameLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        addFragment()
    }

    private fun addFragment() {
        val tabLayout = binding.tabLayoutId
        val viewPager = binding.viewPagerId

        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        adapter.addFragment(ParentBasicFragment(), "Parent 1")
        adapter.addFragment(ParentLivingFragment(), "Parent 2")
        adapter.addFragment(ParentScienceFragment(), "Parent 3")
        adapter.addFragment(ParentMiscFragment(), "Parent 4")
        viewPager.adapter = adapter
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = adapter.getPageTitle(position)
        }.attach()
    }
}