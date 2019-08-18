package com.ludovic.vimont.randomcocktail.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ludovic.vimont.randomcocktail.R
import com.ludovic.vimont.randomcocktail.model.DrinkItem
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class DrinkAdapter(private val drinks: List<DrinkItem>) : RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_drink, parent, false)
        return DrinkViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DrinkViewHolder, position: Int) {
        Picasso.get()
            .load(drinks[position].getImage())
            .into(holder.imageView)
        holder.textView.text = drinks[position].getName()
    }

    override fun getItemCount(): Int {
        return drinks.size
    }

    class DrinkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image_view_drink_image)
        val textView: TextView = itemView.findViewById(R.id.text_view_drink_name)
    }
}