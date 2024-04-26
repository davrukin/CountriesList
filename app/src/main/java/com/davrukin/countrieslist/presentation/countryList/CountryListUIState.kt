package com.davrukin.countrieslist.presentation.countryList

import com.davrukin.countrieslist.domain.model.CountryInfo
import com.davrukin.countrieslist.remote.LoadingState

data class CountryListUIState(
	val countries: List<CountryInfo> = listOf(),
	val loadingState: LoadingState = LoadingState.NONE,
)
