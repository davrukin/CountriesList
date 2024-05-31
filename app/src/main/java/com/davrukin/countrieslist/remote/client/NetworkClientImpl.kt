package com.davrukin.countrieslist.remote.client

import android.content.Context
import com.davrukin.countrieslist.domain.model.CountryInfo

/**
 * Concrete implementation of [NetworkClient] which maps the network model to the UI model
 *
 * @constructor
 * Constructor for an instance of this class
 *
 * @param context uses context to check for network connectivity
 */
class NetworkClientImpl(
	context: Context? = null,
) : NetworkClient {

	private val networkModule = NetworkModule(context)

	override suspend fun getCountries(): List<CountryInfo>? {
		return networkModule
			.downloadFile()
			?.map { country ->
				CountryInfo(
					code = country.code,
					region = country.region,
					name = country.name,
					capital = country.capital,
					flag = country.flag,
				)
			}
	}
}