package com.davrukin.countrieslist.remote.client

import com.davrukin.countrieslist.domain.model.CountryInfo

interface NetworkClient {

	suspend fun getCountries(): List<CountryInfo>?
}