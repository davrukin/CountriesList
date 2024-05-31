package com.davrukin.countrieslist.remote

import android.content.Context
import com.davrukin.countrieslist.domain.model.CountryInfo
import com.davrukin.countrieslist.remote.client.NetworkClientImpl

/**
 * Entry point to the API used in the ViewModel, already having the mapped [CountryInfo] for UI display
 *
 * @constructor
 * Constructor for this class
 *
 * @param context used internally for network connectivity monitoring
 */
class NetworkRepository(
	context: Context? = null,
) {
	private val networkClient = NetworkClientImpl(context)

	suspend fun getCountries(): List<CountryInfo>? {
		return networkClient.getCountries()
	}
}