package com.ludovic.vimont.randomcocktail.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ludovic.vimont.randomcocktail.R
import com.ludovic.vimont.randomcocktail.model.DrinkItem
import com.squareup.picasso.Picasso

class DrinkAdapter(private val drinks: List<DrinkItem>) : RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder>() {
    private var onItemClickListener: OnItemClickListener? = null

    interface OnItemClickListener {
        fun onClick(drink: DrinkItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_drink, parent, false)
        return DrinkViewHolder(
            itemView
        )
    }

    // TODO: add placeHolder
    override fun onBindViewHolder(holder: DrinkViewHolder, position: Int) {
        val drink: DrinkItem = drinks[position]
        Picasso.get()
            .load(drink.getImage())
            .into(holder.imageView)
        holder.textView.text = drink.getName()
        holder.itemView.setOnClickListener {
            onItemClickListener?.onClick(drink)
        }
    }

    override fun getItemCount(): Int {
        return drinks.size
    }

    fun setOnClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    class DrinkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image_view_drink_image)
        val textView: TextView = itemView.findViewById(R.id.text_view_drink_name)
    }
}