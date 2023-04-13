package com.darth.unitconverter.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.darth.unitconverter.R
import com.darth.unitconverter.adapter.ViewPagerAdapter
import com.darth.unitconverter.databinding.ActivityMainBinding
import com.darth.unitconverter.databinding.FragmentParentBasicBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.color.DynamicColors
import com.google.android.material.tabs.TabLayoutMediator

private lateinit var binding : ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var content: FrameLayout? = null

    val icons = arrayOf(
        R.drawable.unit_icon
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        DynamicColors.applyToActivitiesIfAvailable(application);
        addFragment()
    }

    private fun addFragment() {
        val tabLayout = binding.tabLayoutId
        val viewPager = binding.viewPagerId

        val adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        adapter.addFragment(ParentBasicFragment(), "BASIC")
        adapter.addFragment(ParentLivingFragment(), "LIVING")
        adapter.addFragment(ParentScienceFragment(), "SCIENCE")
        adapter.addFragment(ParentMiscFragment(), "MISC")
        viewPager.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when(position){
                0-> {
                    tab.icon = ContextCompat.getDrawable(this@MainActivity, R.drawable.unit_icon)
                }
                1-> {
                    tab.icon = ContextCompat.getDrawable(this@MainActivity, R.drawable.living_icon)
                }
                2-> {
                    tab.icon = ContextCompat.getDrawable(this@MainActivity, R.drawable.science_icon)
                }
                3-> {
                    tab.icon = ContextCompat.getDrawable(this@MainActivity, R.drawable.misc_icon)
                }
            }
            tab.text = adapter.getPageTitle(position)
        }.attach()
    }
}