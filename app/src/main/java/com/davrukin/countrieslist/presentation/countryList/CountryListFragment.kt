package com.davrukin.countrieslist.presentation.countryList

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.davrukin.countrieslist.R
import com.davrukin.countrieslist.data.Country

class CountryListFragment : Fragment(R.layout.fragment_country_list) {

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		val bundle = requireArguments()

		val recyclerView = view.findViewById<RecyclerView>(R.id.country_list)

		recyclerView.layoutManager = LinearLayoutManager(view.context)

		val countries = emptyList<Country>()

		val adapter = CountryListAdapter(countries)

		recyclerView.adapter = adapter
	}
}