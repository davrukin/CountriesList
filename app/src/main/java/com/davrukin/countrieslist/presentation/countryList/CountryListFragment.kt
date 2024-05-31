package com.davrukin.countrieslist.presentation.countryList

import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.davrukin.countrieslist.R
import com.davrukin.countrieslist.domain.model.CountryInfo
import com.davrukin.countrieslist.presentation.components.ErrorDialog
import com.davrukin.countrieslist.presentation.components.LoadingDialog
import com.davrukin.countrieslist.remote.LoadingState
import com.davrukin.countrieslist.remote.NetworkRepository
import com.davrukin.countrieslist.remote.model.Country
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class CountryListFragment : Fragment(R.layout.fragment_country_list) {

	//private val viewModel = CountryListViewModel(NetworkRepository(context))
	private val viewModel: CountryListViewModel by activityViewModels()
	//private lateinit var viewModel: CountryListViewModel

	private var recyclerView: RecyclerView? = null
	private var recyclerViewState: Parcelable? = null

	private val loadingDialog = LoadingDialog()
	private lateinit var errorDialog: ErrorDialog

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		//viewModel = ViewModelProvider(requireActivity()).get(CountryListViewModel::class.java)
		errorDialog = ErrorDialog(
			onOk = viewModel::resetLoadingState,
			onReload = viewModel::refreshCountriesList
		)

		//val bundle = requireArguments()
		// this is an option if using fragment programmatically to send args

		// https://www.geeksforgeeks.org/android-recyclerview-in-kotlin/
		recyclerView = view.findViewById<RecyclerView>(R.id.country_list)

		recyclerView?.layoutManager = LinearLayoutManager(view.context)

		val countries = emptyList<CountryInfo>()

		val adapter = CountryListAdapter(countries)

		// https://stackoverflow.com/a/63987580
		adapter.stateRestorationPolicy = RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY

		recyclerView?.adapter = adapter

		viewModel.refreshCountriesList()

		lifecycleScope.launch {
			// https://developer.android.com/kotlin/flow/stateflow-and-sharedflow
			viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
				viewModel.uiState.collectLatest {
					adapter.updateCountries(it.countries)

					when (it.loadingState) {
						is LoadingState.ERROR -> {
							loadingDialog.cancel()
							errorDialog.show(childFragmentManager)
						}
						is LoadingState.LOADING -> {
							errorDialog.cancel()
							loadingDialog.show(childFragmentManager)
						}
						is LoadingState.SUCCESS,
						is LoadingState.NONE -> {
							loadingDialog.cancel()
						}
					}
				}
			}
		}
	}

	// https://stackoverflow.com/a/65645518

	override fun onPause() {
		super.onPause()

		recyclerViewState = recyclerView?.layoutManager?.onSaveInstanceState()
	}

	override fun onResume() {
		super.onResume()

		recyclerView?.layoutManager?.onRestoreInstanceState(recyclerViewState)
	}
}