package com.darth.unitconverter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.darth.unitconverter.R

class ConversionLengthAdapter(private val conversionList: List<String>) :
    RecyclerView.Adapter<ConversionLengthAdapter.ConversionViewHolder>() {

    class ConversionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val conversionItemText: TextView = itemView.findViewById(R.id.conversionItemText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConversionViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.conversion_length_item, parent, false)
        return ConversionViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ConversionViewHolder, position: Int) {
        val conversionItem = conversionList[position]
        holder.conversionItemText.text = conversionItem


        // Set background color based on position
//        val backgroundColorResId = if (position % 2 == 0) {
//            R.color.light_grey
//        } else {
//            R.color.medium_grey
//        }
//        holder.itemView.setBackgroundResource(backgroundColorResId)
    }

    override fun getItemCount(): Int {
        return conversionList.size
    }
}