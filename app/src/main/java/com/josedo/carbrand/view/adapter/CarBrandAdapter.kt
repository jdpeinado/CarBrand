package com.josedo.carbrand.view.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.josedo.carbrand.R
import com.josedo.carbrand.model.CarBrand

class CarBrandAdapter : RecyclerView.Adapter<CarBrandAdapter.ViewHolder>() {

    var listBrands = ArrayList<CarBrand>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarBrandAdapter.ViewHolder =
        ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_carbrand, parent, false)
        )

    override fun getItemCount(): Int = listBrands.size

    override fun onBindViewHolder(holder: CarBrandAdapter.ViewHolder, position: Int) {
        val brand = listBrands[position]
        Log.d("tag", listBrands.toString())

        holder.tvBrandName.text = brand.nameBrand
    }

    fun updateData(data: List<CarBrand>){
        listBrands.clear()
        listBrands.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvBrandName = itemView.findViewById<TextView>(R.id.tvBrandName)

    }
}