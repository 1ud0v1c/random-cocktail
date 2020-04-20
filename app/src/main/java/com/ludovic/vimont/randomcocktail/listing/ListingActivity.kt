package com.ludovic.vimont.randomcocktail.listing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ludovic.vimont.randomcocktail.R
import com.ludovic.vimont.randomcocktail.model.Constants
import com.ludovic.vimont.randomcocktail.model.DrinkItem
import com.ludovic.vimont.randomcocktail.random.RandomActivity
import com.ludovic.vimont.randomcocktail.ui.adapter.DrinkAdapter
import com.ludovic.vimont.randomcocktail.ui.view.ItemOffsetDecoration

class ListingActivity : AppCompatActivity(), ListingView, DrinkAdapter.OnItemClickListener {
    private val listingPresenter = ListingPresenter(this, ListingInteractor())
    private lateinit var recyclerView: RecyclerView

    // TODO: add filtering
    //      by name search
    //      by type (alcohol, non)
    // TODO: add favorite features
    // TODO: display stateful loading
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listing)
        recyclerView = findViewById(R.id.recycler_view)
        onCreate()
    }

    override fun onCreate() {
        listingPresenter.loadDrinks(applicationContext)
    }

    override fun setCocktails(drinks: List<DrinkItem>) {
        val drinkAdapter = DrinkAdapter(drinks)
        recyclerView.adapter = drinkAdapter
        recyclerView.layoutManager = GridLayoutManager(applicationContext, 3)
        recyclerView.addItemDecoration(
            ItemOffsetDecoration(
                resources.getDimension(R.dimen.grid_item_padding).toInt()
            )
        )
        drinkAdapter.setOnClickListener(this)
    }

    override fun onClick(drink: DrinkItem) {
        val intent = Intent(this, RandomActivity::class.java)
        intent.putExtra(Constants.KEY_DRINK_ITEM, drink)
        startActivity(intent)
    }
}