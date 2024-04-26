package com.davrukin.countrieslist.presentation.countryList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davrukin.countrieslist.remote.NetworkRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CountryListViewModel(
	private val networkRepository: NetworkRepository,
) : ViewModel() {

	private val _uiState = MutableStateFlow(CountryListUIState())
	val uiState: StateFlow<CountryListUIState> = _uiState.asStateFlow()

	fun refreshCountriesList() {
		viewModelScope.launch {
			_uiState.update {
				it.copy(isLoading = true)
			}

			val countries = networkRepository.getCountries()

			_uiState.update { it ->
				it.copy(
					countries = countries ?: listOf(),
					isLoading = false,
					isError = countries != null,
				)
			}
		}
	}
}