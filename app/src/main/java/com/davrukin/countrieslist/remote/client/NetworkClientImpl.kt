package com.davrukin.countrieslist.remote.client

import android.content.Context
import com.davrukin.countrieslist.data.Country

class NetworkClientImpl(
	context: Context?,
) : NetworkClient {

	private val networkModule = NetworkModule(context)

	override suspend fun getCountries(): List<Country>? {
		return networkModule.downloadFile()
	}
}