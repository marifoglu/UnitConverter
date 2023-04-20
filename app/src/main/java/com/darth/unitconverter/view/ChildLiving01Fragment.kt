package com.darth.unitconverter.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.darth.unitconverter.R
import com.darth.unitconverter.adapter.ShoeSizeAdapter
import com.darth.unitconverter.databinding.FragmentChildLiving01Binding
import com.darth.unitconverter.model.ShoeSize

class ChildLiving01Fragment : Fragment() {

    private var _binding: FragmentChildLiving01Binding? = null
    private val binding get() = _binding!!

    private lateinit var shoeSizesAdapter: ShoeSizeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChildLiving01Binding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerViewShoeSizes = view.findViewById<RecyclerView>(R.id.recyclerViewShoeSizes)

        // Create the list of shoe sizes
        val shoeSizes = listOf(
            ShoeSize("3 1/2", "2", "35", "8 1/2", "215"),
            ShoeSize("4", "2 1/2", "35 1/2", "8 5/8", "219"),
            ShoeSize("4 1/2", "3", "36", "8 3/4", "223"),
            ShoeSize("5", "3 1/2", "37", "9", "228"),
            ShoeSize("5 1/2", "4", "37 1/2", "9 1/8", "232"),
            ShoeSize("6", "4 1/2", "38", "9 1/4", "236"),
            ShoeSize("6 1/2", "5", "39", "9 1/2", "241"),
            ShoeSize("7", "5 1/2", "40", "9 5/8", "245"),
            ShoeSize("7 1/2", "6", "40 1/2", "9 3/4", "249"),
            ShoeSize("8", "6 1/2", "41", "10", "253"),
            ShoeSize("8 1/2", "7", "42", "10 1/8", "258"),
            ShoeSize("9", "7 1/2", "42 1/2", "10 1/4", "262"),
            ShoeSize("9 1/2", "8", "43", "10 1/2", "266"),
            ShoeSize("10", "8 1/2", "44", "10 5/8", "270"),
            ShoeSize("10 1/2", "9", "44 1/2", "10 3/4", "274"),
            ShoeSize("11", "9 1/2", "45", "11", "279"),
            ShoeSize("11 1/2", "10", "45 1/2", "11 1/8", "283"),
            ShoeSize("12", "10 1/2", "46", "11 1/4", "287"),
            ShoeSize("13", "11 1/2", "47 1/2", "11 1/2", "295"),
            ShoeSize("14", "12 1/2", "49", "11 3/4", "302"),
            ShoeSize("15", "13 1/2", "50 1/2", "12", "310")
        )

        shoeSizesAdapter = ShoeSizeAdapter(shoeSizes)
        binding.recyclerViewShoeSizes.adapter = shoeSizesAdapter
        binding.recyclerViewShoeSizes.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
