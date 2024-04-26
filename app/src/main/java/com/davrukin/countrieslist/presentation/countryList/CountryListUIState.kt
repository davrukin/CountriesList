package com.davrukin.countrieslist.presentation.countryList

import com.davrukin.countrieslist.domain.model.CountryInfo

data class CountryListUIState(
	val countries: List<CountryInfo> = listOf(),
	val isLoading: Boolean = false,
	val isError: Boolean = false,
)
