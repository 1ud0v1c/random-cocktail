package com.ludovic.vimont.randomcocktail.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ludovic.vimont.randomcocktail.R
import com.ludovic.vimont.randomcocktail.interactor.ListingInteractor
import com.ludovic.vimont.randomcocktail.presenter.ListingPresenter
import com.ludovic.vimont.randomcocktail.view.ListingView

class ListingActivity : AppCompatActivity(), ListingView {
    private val listingPresenter = ListingPresenter(this, ListingInteractor())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listing)
        onCreate()
    }

    override fun onCreate() {
        listingPresenter.onCreate()
    }
}