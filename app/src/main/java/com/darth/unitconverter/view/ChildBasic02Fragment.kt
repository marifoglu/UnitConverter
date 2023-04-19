package com.darth.unitconverter.view

import android.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.darth.unitconverter.adapter.ConversionWeightAdapter
import com.darth.unitconverter.databinding.FragmentChildBasic02Binding
import com.darth.unitconverter.model.ConversionData

class ChildBasic02Fragment : Fragment() {

    private val weightUnits = arrayOf(
        "Kilogram",
        "Gram",
        "Milligram",
        "Pound",
        "Ounce"
    )

    private val conversionFactors = mapOf(
        "Kilogram" to 1.0,
        "Gram" to 0.001,
        "Milligram" to 0.000001,
        "Pound" to 0.453592,
        "Ounce" to 0.0283495
    )
    private var _binding: FragmentChildBasic02Binding? = null
    private val binding get() = _binding!!

    private val conversionList = ArrayList<ConversionData>()
    private lateinit var conversionAdapter: ConversionWeightAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChildBasic02Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        conversionAdapter = ConversionWeightAdapter(conversionList)
        binding.conversionListRecycler.adapter = conversionAdapter
        binding.conversionListRecycler.layoutManager = LinearLayoutManager(requireContext())

        val unitSpinner = binding.unitSpinner
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.simple_spinner_item,
            weightUnits
        )
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)
        unitSpinner.adapter = adapter
        unitSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                convert()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
            }
        }

        binding.conversionItemText.doAfterTextChanged { text ->
            convert()
        }
    }

    private fun convert() {
        val inputValueText = binding.conversionItemText.text.toString()
        val inputValue = inputValueText.toDoubleOrNull()
        val selectedUnit = binding.unitSpinner.selectedItem.toString()

        if (inputValue != null) {
            val formattedInputValue = if (inputValue % 1 == 0.0) {
                inputValue.toInt().toString()
            } else {
                inputValue.toString()
            }

            conversionList.clear()

            weightUnits.forEach { unit ->
                val result = convertWeight(inputValue, selectedUnit, unit)
                val conversionData = ConversionData(result, unit)
                conversionList.add(conversionData)
            }

            conversionAdapter.notifyDataSetChanged()
        }
    }

    private fun convertWeight(value: Double, fromUnit: String, toUnit: String): String {
        val fromFactor = conversionFactors[fromUnit]
        val toFactor = conversionFactors[toUnit]

        if (fromFactor != null && toFactor != null) {
            val baseValue = value * fromFactor
            val result = baseValue / toFactor

            // Format the result with two decimal places
            val formattedResult = String.format("%.2f", result)

            return formattedResult
        }

        return "0" // or handle the case when conversion factors are not available
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}