package com.davrukin.countrieslist.remote.client

import com.davrukin.countrieslist.data.Constants
import com.davrukin.countrieslist.data.Country
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream

class NetworkClientRetrofit : NetworkClient {

	private val retrofitModule = RetrofitModule()

	//private val service = retrofitModule.retrofit.create(RetrofitService::class.java)

	@OptIn(ExperimentalSerializationApi::class)
	override suspend fun getCountries(): List<Country> {
		//return service.getCountries()
		/*return service.downloadFile(Constants.URL).body()?.byteStream()?.let {
			Json.decodeFromStream<List<Country>>(it)
		} ?: listOf()*/
		return retrofitModule.downloadFile()
	}
}