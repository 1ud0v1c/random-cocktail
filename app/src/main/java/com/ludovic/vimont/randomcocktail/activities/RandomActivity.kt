package com.ludovic.vimont.randomcocktail.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ludovic.vimont.randomcocktail.R
import com.ludovic.vimont.randomcocktail.interactor.RandomInteractor
import com.ludovic.vimont.randomcocktail.model.DrinkItem
import com.ludovic.vimont.randomcocktail.presenter.RandomPresenter
import com.ludovic.vimont.randomcocktail.view.IngredientAdapter
import com.ludovic.vimont.randomcocktail.view.RandomView
import com.squareup.picasso.Picasso
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.core.content.ContextCompat


class RandomActivity : AppCompatActivity(), RandomView {
    private val randomPresenter = RandomPresenter(this, RandomInteractor())
    private lateinit var textViewDrinkName: TextView
    private lateinit var imageViewDrinkImage: ImageView
    private lateinit var textViewDrinkInstructions: TextView
    private lateinit var recyclerViewIngredients: RecyclerView

    // TODO: add refresh button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_random)

        textViewDrinkName = findViewById(R.id.text_view_drink_name)
        imageViewDrinkImage = findViewById(R.id.image_view_drink_image)
        textViewDrinkInstructions = findViewById(R.id.text_view_drink_instructions)
        recyclerViewIngredients = findViewById(R.id.recycler_view_ingredients)

        onCreate()
    }

    override fun onCreate() {
        randomPresenter.onCreate(applicationContext)
    }

    override fun displayCocktail(drinkItem: DrinkItem) {
        textViewDrinkName.text = drinkItem.getName()
        Picasso.get()
            .load(drinkItem.getImage())
            .into(imageViewDrinkImage)
        textViewDrinkInstructions.text = drinkItem.getInstructions()
        recyclerViewIngredients.adapter = IngredientAdapter(drinkItem.getIngredients())
        recyclerViewIngredients.layoutManager = LinearLayoutManager(applicationContext, RecyclerView.VERTICAL, false)
        val itemDecoration = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
        val drawable = ContextCompat.getDrawable(applicationContext, R.drawable.recycler_view_divider)
        drawable?.let {
            itemDecoration.setDrawable(it)
        }
        recyclerViewIngredients.addItemDecoration(itemDecoration)
    }
}