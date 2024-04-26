package com.davrukin.countrieslist.remote

import android.content.Context
import com.davrukin.countrieslist.domain.model.CountryInfo
import com.davrukin.countrieslist.remote.client.NetworkClientImpl

class NetworkRepository(
	context: Context?,
) {
	private val networkClient = NetworkClientImpl(context)

	suspend fun getCountries(): List<CountryInfo>? {
		return networkClient.getCountries()
	}
}