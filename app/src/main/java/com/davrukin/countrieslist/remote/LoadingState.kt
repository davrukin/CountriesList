package com.davrukin.countrieslist.remote

/**
 * Loading state of the UI
 */
sealed class LoadingState {
	/**
	 * Initial state of the UI
	 */
	data object NONE : LoadingState()

	/**
	 * Currently loading data
	 */
	data object LOADING : LoadingState()

	/**
	 * Data has been loaded successfully
	 */
	data object SUCCESS : LoadingState()

	/**
	 * There was an error loading the data
	 */
	data object ERROR : LoadingState()
	// prefer this: data class ERROR(val exception: Exception) : LoadingState()
}