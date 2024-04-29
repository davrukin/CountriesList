package com.davrukin.countrieslist.presentation.countryList

import com.davrukin.countrieslist.domain.model.CountryInfo
import com.davrukin.countrieslist.remote.LoadingState

/**
 * Model of the current state of the UI
 *
 * @property countries the current list of countries
 * @property loadingState the current state of page load
 */
data class CountryListUIState(
	val countries: List<CountryInfo> = listOf(),
	val loadingState: LoadingState = LoadingState.NONE,
)
