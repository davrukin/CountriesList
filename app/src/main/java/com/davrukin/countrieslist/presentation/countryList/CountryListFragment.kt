package com.davrukin.countrieslist.presentation.countryList

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.davrukin.countrieslist.R
import com.davrukin.countrieslist.data.Country
import com.davrukin.countrieslist.remote.client.NetworkClientRetrofit
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CountryListFragment : Fragment(R.layout.fragment_country_list) {

	private val viewModel = CountryListViewModel(NetworkClientRetrofit())

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		//val bundle = requireArguments()

		val recyclerView = view.findViewById<RecyclerView>(R.id.country_list)

		recyclerView.layoutManager = LinearLayoutManager(view.context)

		val countries = emptyList<Country>()

		val adapter = CountryListAdapter(countries)

		recyclerView.adapter = adapter

		viewModel.refreshCountriesList()

		lifecycleScope.launch {
			viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
				viewModel.uiState.collect {
					adapter.updateCountries(it.countries)
				}
			}
		}
	}
}