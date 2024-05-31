package com.davrukin.countrieslist.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.davrukin.countrieslist.R
import com.davrukin.countrieslist.presentation.countryList.CountryListViewModel

class MainActivity : AppCompatActivity(R.layout.activity_main) {

	//private val viewModel: CountryListViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		enableEdgeToEdge()

		// https://developer.android.com/guide/fragments/create#add-xml

		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
			val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
			insets
		}
	}

	/*
	- two fragments on screen, one flags, the other existing list
	- single view model to request once
	 */
}