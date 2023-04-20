package com.darth.unitconverter.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.darth.unitconverter.R
import com.darth.unitconverter.model.ShoeSize

class ShoeSizeAdapter(private val shoeSizes: List<ShoeSize>) :
    RecyclerView.Adapter<ShoeSizeAdapter.ShoeSizeViewHolder>() {

    class ShoeSizeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textUsSize: TextView = itemView.findViewById(R.id.textUsSize)
        val textUkSize: TextView = itemView.findViewById(R.id.textUkSize)
        val textEuSize: TextView = itemView.findViewById(R.id.textEuSize)
        val textInchSize: TextView = itemView.findViewById(R.id.textInchSize)
        val textMmSize: TextView = itemView.findViewById(R.id.textMmSize)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoeSizeViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_shoe_size, parent, false)
        return ShoeSizeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ShoeSizeViewHolder, position: Int) {
        val shoeSize = shoeSizes[position]
        holder.textUsSize.text = shoeSize.usSize
        holder.textUkSize.text = shoeSize.ukSize
        holder.textEuSize.text = shoeSize.euSize
        holder.textInchSize.text = shoeSize.inchSize
        holder.textMmSize.text = shoeSize.mmSize
    }

    override fun getItemCount(): Int {
        return shoeSizes.size
    }
}
