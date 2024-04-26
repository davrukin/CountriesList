package com.davrukin.countrieslist.remote.client

import android.content.Context
import com.davrukin.countrieslist.domain.model.CountryInfo

class NetworkClientImpl(
	context: Context?,
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
				)
			}
	}
}