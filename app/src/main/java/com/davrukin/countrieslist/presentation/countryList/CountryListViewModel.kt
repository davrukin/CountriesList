package com.davrukin.countrieslist.presentation.countryList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.davrukin.countrieslist.remote.LoadingState
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

	fun resetLoadingState() {
		viewModelScope.launch {
			_uiState.update { uiState ->
				uiState.copy(loadingState = LoadingState.NONE)
			}
		}
	}

	fun refreshCountriesList() {
		viewModelScope.launch {
			_uiState.update { uiState ->
				uiState.copy(loadingState = LoadingState.LOADING)
			}

			val countries = networkRepository.getCountries()
			// would potentially throw exception and put it into the ERROR state

			_uiState.update { uiState ->
				val loadingState = when (countries) {
					null -> LoadingState.ERROR
					else -> LoadingState.SUCCESS // also applies to empty
				}

				uiState.copy(
					countries = countries ?: listOf(),
					loadingState = loadingState,
				)
			}
		}
	}
}