package com.davrukin.countrieslist.remote.client

import com.davrukin.countrieslist.domain.model.CountryInfo

/**
 * List of network functions to be performed
 */
interface NetworkClient {

	/**
	 * Gets a nullable list of countries from the JSON file
	 *
	 * @return null if error loading, otherwise a list of countries
	 */
	suspend fun getCountries(): List<CountryInfo>?
}