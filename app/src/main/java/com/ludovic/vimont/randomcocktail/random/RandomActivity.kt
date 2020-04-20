package com.ludovic.vimont.randomcocktail.random

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ludovic.vimont.randomcocktail.R
import com.ludovic.vimont.randomcocktail.model.DrinkItem
import com.ludovic.vimont.randomcocktail.ui.adapter.IngredientAdapter
import com.squareup.picasso.Picasso
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.core.content.ContextCompat
import com.ludovic.vimont.randomcocktail.model.Constants

class RandomActivity : AppCompatActivity(),
    RandomView {
    private val randomPresenter =
        RandomPresenter(
            this,
            RandomInteractor()
        )
    private lateinit var textViewDrinkName: TextView
    private lateinit var imageViewDrinkImage: ImageView
    private lateinit var textViewDrinkInstructions: TextView
    private lateinit var recyclerViewIngredients: RecyclerView

    // TODO: display stateful loading
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random)

        textViewDrinkName = findViewById(R.id.text_view_drink_name)
        imageViewDrinkImage = findViewById(R.id.image_view_drink_image)
        textViewDrinkInstructions = findViewById(R.id.text_view_drink_instructions)
        recyclerViewIngredients = findViewById(R.id.recycler_view_ingredients)
        addIemDecorationToRecyclerView()

        onCreate()
    }

    private fun addIemDecorationToRecyclerView() {
        val itemDecoration = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
        val drawable = ContextCompat.getDrawable(applicationContext, R.drawable.recycler_view_divider)
        drawable?.let {
            itemDecoration.setDrawable(it)
        }
        recyclerViewIngredients.addItemDecoration(itemDecoration)
    }

    override fun onCreate() {
        randomPresenter.loadDrink(applicationContext, intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.action_bar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.item_action_refresh -> onRefresh()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onRefresh() {
        if (intent.hasExtra(Constants.KEY_DRINK_ITEM)) {
            intent.removeExtra(Constants.KEY_DRINK_ITEM)
        }
        randomPresenter.loadDrink(applicationContext, intent)
    }

    // TODO: add placeHolder
    // TODO: increase size text
    override fun displayCocktail(drinkItem: DrinkItem) {
        textViewDrinkName.text = drinkItem.getName()
        Picasso.get()
            .load(drinkItem.getImage())
            .into(imageViewDrinkImage)
        textViewDrinkInstructions.text = drinkItem.getInstructions()
        recyclerViewIngredients.adapter =
            IngredientAdapter(
                drinkItem.getIngredients()
            )
        recyclerViewIngredients.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
    }
}