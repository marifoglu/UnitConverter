package com.darth.unitconverter.adapter

import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.darth.unitconverter.R
import com.darth.unitconverter.model.ConversionData

class ConversionLengthAdapter(private val conversionList: List<ConversionData>) :
    RecyclerView.Adapter<ConversionLengthAdapter.ConversionViewHolder>() {

    class ConversionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val conversionItemText: TextView = itemView.findViewById(R.id.conversionItemText)
        val conversionItemName: TextView = itemView.findViewById(R.id.conversionItemName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversionViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.conversion_length_item, parent, false)
        return ConversionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ConversionViewHolder, position: Int) {
        val conversionData = conversionList[position]
        holder.conversionItemText.text = conversionData.value
        holder.conversionItemName.text = conversionData.unit
    }

    override fun getItemCount(): Int {
        return conversionList.size
    }
}