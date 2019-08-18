package com.ludovic.vimont.randomcocktail.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ludovic.vimont.randomcocktail.R
import com.ludovic.vimont.randomcocktail.model.Ingredient

class IngredientAdapter(private val ingredients: List<Ingredient>) : RecyclerView.Adapter<IngredientAdapter.IngredientViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_ingredient, parent, false)
        return IngredientViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: IngredientViewHolder, position: Int) {
        holder.textViewIngredientName.text = ingredients[position].getName()
        holder.textViewIngredientMeasure.text = ingredients[position].getMeasure()
    }

    override fun getItemCount(): Int {
        return ingredients.size
    }

    class IngredientViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textViewIngredientName: TextView = itemView.findViewById(R.id.text_view_ingredient_name)
        val textViewIngredientMeasure: TextView = itemView.findViewById(R.id.text_view_ingredient_measure)
    }
}