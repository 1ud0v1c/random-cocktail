package com.ludovic.vimont.randomcocktail.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ludovic.vimont.randomcocktail.R
import com.ludovic.vimont.randomcocktail.interactor.ListingInteractor
import com.ludovic.vimont.randomcocktail.model.DrinkItem
import com.ludovic.vimont.randomcocktail.presenter.ListingPresenter
import com.ludovic.vimont.randomcocktail.view.DrinkAdapter
import com.ludovic.vimont.randomcocktail.view.ItemOffsetDecoration
import com.ludovic.vimont.randomcocktail.view.ListingView

class ListingActivity : AppCompatActivity(), ListingView {
    private val listingPresenter = ListingPresenter(this, ListingInteractor())
    private lateinit var recyclerView: RecyclerView

    // TODO: add filtering by name
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
        recyclerView.adapter = DrinkAdapter(drinks)
        recyclerView.layoutManager = GridLayoutManager(applicationContext, 3)
        recyclerView.addItemDecoration(ItemOffsetDecoration(resources.getDimension(R.dimen.grid_item_padding).toInt()))
    }
}