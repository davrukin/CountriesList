package com.davrukin.countrieslist.remote.client

import com.davrukin.countrieslist.data.Country

interface NetworkClient {

	suspend fun getCountries(): List<Country>?
}