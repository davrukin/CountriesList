package com.davrukin.countrieslist.presentation.countryList

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.davrukin.countrieslist.R
import com.davrukin.countrieslist.domain.model.CountryInfo
import com.davrukin.countrieslist.remote.NetworkRepository
import kotlinx.coroutines.launch

class CountryListFragment : Fragment(R.layout.fragment_country_list) {

	private val viewModel = CountryListViewModel(NetworkRepository(context))

	private var recyclerView: RecyclerView? = null
	private var recyclerViewState: Parcelable? = null

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		//val bundle = requireArguments()

		recyclerView = view.findViewById<RecyclerView>(R.id.country_list)

		recyclerView?.layoutManager = LinearLayoutManager(view.context)

		val countries = emptyList<CountryInfo>()

		val adapter = CountryListAdapter(countries)

		adapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

		recyclerView?.adapter = adapter

		viewModel.refreshCountriesList()

		lifecycleScope.launch {
			viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
				viewModel.uiState.collect {
					adapter.updateCountries(it.countries)
				}
			}
		}
	}

	override fun onPause() {
		super.onPause()

		recyclerViewState = recyclerView?.layoutManager?.onSaveInstanceState()
	}

	override fun onResume() {
		super.onResume()

		recyclerView?.layoutManager?.onRestoreInstanceState(recyclerViewState)
	}
}