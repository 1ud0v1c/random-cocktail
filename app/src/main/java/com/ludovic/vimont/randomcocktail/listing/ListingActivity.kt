package com.ludovic.vimont.randomcocktail.listing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import androidx.core.content.ContextCompat
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
    private lateinit var drinkAdapter: DrinkAdapter

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
        drinkAdapter = DrinkAdapter(drinks)
        recyclerView.adapter = drinkAdapter
        recyclerView.layoutManager = GridLayoutManager(applicationContext, 3)
        recyclerView.addItemDecoration(ItemOffsetDecoration(resources.getDimension(R.dimen.grid_item_padding).toInt()))
        drinkAdapter.setOnClickListener(this)
    }

    override fun onClick(drink: DrinkItem) {
        val intent = Intent(this, RandomActivity::class.java)
        intent.putExtra(Constants.KEY_DRINK_ITEM, drink)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.listing_activity_action_bar_menu, menu)
        val menuItemActionSearch: MenuItem = menu.findItem(R.id.item_action_search)
        val searchView: SearchView = menuItemActionSearch.actionView as SearchView
        searchView.isIconifiedByDefault = false
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(optionalNewText: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(optionalNewText: String?): Boolean {
                optionalNewText?.let { newText ->
                    drinkAdapter.filter.filter(newText)
                }
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_action_alcohol -> {
                drinkAdapter.switchAlcoholIncludedState()
                if (drinkAdapter.isAlcoholIncluded()) {
                    item.icon = ContextCompat.getDrawable(applicationContext, R.drawable.ic_alcohol)
                } else {
                    item.icon = ContextCompat.getDrawable(applicationContext, R.drawable.ic_no_alcohol)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }
}