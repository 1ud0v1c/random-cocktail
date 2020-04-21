package com.ludovic.vimont.randomcocktail.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ludovic.vimont.randomcocktail.R
import com.ludovic.vimont.randomcocktail.model.DrinkItem
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList

class DrinkAdapter(private val drinks: List<DrinkItem>) : RecyclerView.Adapter<DrinkAdapter.DrinkViewHolder>(),
    Filterable {
    private var isAlcoholIncluded: Boolean = true
    private var previousSearch: String = ""
    private var filteredDrinks: List<DrinkItem> = drinks
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
        val drink: DrinkItem = filteredDrinks[position]
        Picasso.get()
            .load(drink.getImage())
            .into(holder.imageView)
        holder.textView.text = drink.getName()
        holder.itemView.setOnClickListener {
            onItemClickListener?.onClick(drink)
        }
    }

    override fun getItemCount(): Int {
        return filteredDrinks.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val newSearch: String = charSequence.toString()
                val filterResults = FilterResults()
                filterResults.values = filterDrinks(newSearch)
                previousSearch = newSearch
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                filteredDrinks = filterResults.values as ArrayList<DrinkItem>
                notifyDataSetChanged()
            }
        }
    }

    private fun filterDrinks(search: String): List<DrinkItem> {
        val filteredList = ArrayList<DrinkItem>()
        if (search.isEmpty()) {
            for (drink: DrinkItem in drinks) {
                if (!isAlcoholIncluded && !drink.containsAlcohol()) {
                    filteredList.add(drink)
                } else if (isAlcoholIncluded) {
                    filteredList.add(drink)
                }
            }
        } else {
            for (drink: DrinkItem in drinks) {
                if (drink.getName().toLowerCase(Locale.getDefault()).contains(search.toLowerCase(Locale.getDefault()))) {
                    if (!isAlcoholIncluded && !drink.containsAlcohol()) {
                        filteredList.add(drink)
                    } else if (isAlcoholIncluded) {
                        filteredList.add(drink)
                    }
                }
            }
        }
        return filteredList
    }

    fun setOnClickListener(onItemClickListener: OnItemClickListener) {
        this.onItemClickListener = onItemClickListener
    }

    fun switchAlcoholIncludedState() {
        isAlcoholIncluded = !isAlcoholIncluded
        filteredDrinks = filterDrinks(previousSearch)
        notifyDataSetChanged()
    }

    fun isAlcoholIncluded(): Boolean {
        return isAlcoholIncluded
    }

    class DrinkViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.image_view_drink_image)
        val textView: TextView = itemView.findViewById(R.id.text_view_drink_name)
    }
}