package com.davrukin.countrieslist.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.add
import androidx.fragment.app.commit
import com.davrukin.countrieslist.R
import com.davrukin.countrieslist.presentation.countryList.CountryListFragment

class MainActivity : AppCompatActivity(R.layout.activity_main) {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)

		enableEdgeToEdge()
		//setContentView(R.layout.activity_main)

		/*if (savedInstanceState != null) {
			val bundle = bundleOf()
			supportFragmentManager.commit {
				setReorderingAllowed(true)
				add<CountryListFragment>(R.id.fragment_container_view, args = bundle)
			}
		}*/

		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
			val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
			insets
		}
	}
}