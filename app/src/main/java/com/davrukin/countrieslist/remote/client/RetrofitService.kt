package com.davrukin.countrieslist.remote.client

import com.davrukin.countrieslist.data.Country
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url

interface RetrofitService {

	@GET
	suspend fun getCountries(): List<Country>

	@Streaming
	@GET
	suspend fun downloadFile(@Url fileUrl: String): Response<ResponseBody>
}