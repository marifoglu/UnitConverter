import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.darth.unitconverter.R
import com.darth.unitconverter.adapter.ConversionLengthAdapter
import com.darth.unitconverter.databinding.FragmentChildBasic01Binding
import com.darth.unitconverter.model.ConversionData

class ChildBasic01Fragment : Fragment() {

    private val lengthUnits = arrayOf(
        "Kilometer",
        "Meter",
        "Centimeter",
        "Micrometer",
        "Nanometer",
        "Mile",
        "Yard",
        "Foot",
        "Inch",
        "Light Year"
    )

    private val conversionFactors = mapOf(
        "Kilometer" to 1000.0,
        "Meter" to 1.0,
        "Centimeter" to 0.01,
        "Micrometer" to 0.000001,
        "Nanometer" to 0.000000001,
        "Mile" to 1609.34,
        "Yard" to 0.9144,
        "Foot" to 0.3048,
        "Inch" to 0.0254,
        "Light Year" to 9.461e15
    )

    private var _binding: FragmentChildBasic01Binding? = null
    private val binding get() = _binding!!

    private val conversionList = ArrayList<ConversionData>()
    private lateinit var conversionAdapter: ConversionLengthAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChildBasic01Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        conversionAdapter = ConversionLengthAdapter(conversionList)
        binding.conversionListRecycler.adapter = conversionAdapter
        binding.conversionListRecycler.layoutManager = LinearLayoutManager(requireContext())

        val unitSpinner = binding.unitSpinner
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, lengthUnits)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        unitSpinner.adapter = adapter
        unitSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
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

            lengthUnits.forEach { unit ->
                val result = convertLength(inputValue, selectedUnit, unit)
                val conversionData = ConversionData(result, unit)
                conversionList.add(conversionData)
            }

            conversionAdapter.notifyDataSetChanged()
        }
    }

    private fun convertLength(value: Double, fromUnit: String, toUnit: String): String {
        val fromFactor = conversionFactors[fromUnit]
        val toFactor = conversionFactors[toUnit]

        if (fromFactor != null && toFactor != null) {
            val meterValue = value * fromFactor
            val result = meterValue / toFactor

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
