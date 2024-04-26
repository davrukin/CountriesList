package com.davrukin.countrieslist.presentation.countryList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davrukin.countrieslist.remote.client.NetworkClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CountryListViewModel(
	private val networkClient: NetworkClient,
) : ViewModel() {

	private val _uiState = MutableStateFlow(CountryListUIState())
	val uiState = _uiState.asStateFlow()

	fun refreshCountriesList() {
		viewModelScope.launch {
			_uiState.update { it ->
				it.copy(countries = networkClient.getCountries())
			}
		}
	}
}