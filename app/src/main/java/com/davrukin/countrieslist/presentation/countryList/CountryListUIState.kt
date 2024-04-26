package com.davrukin.countrieslist.presentation.countryList

import com.davrukin.countrieslist.data.Country

data class CountryListUIState(
	val countries: List<Country> = listOf(),
	val isLoading: Boolean = false,
	val isError: Boolean = false,
)
