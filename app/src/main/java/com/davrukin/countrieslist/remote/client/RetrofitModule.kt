package com.davrukin.countrieslist.remote.client

import android.content.Context
import android.util.Log
import com.davrukin.countrieslist.data.Constants
import com.davrukin.countrieslist.data.Country
import com.davrukin.countrieslist.remote.networkMonitor.live.LiveNetworkMonitorInterceptor
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import okhttp3.Dispatcher
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import okio.IOException
import retrofit2.Retrofit

class RetrofitModule(context: Context) {

	private val okHttpClient: OkHttpClient by lazy {
		val loggingInterceptor = HttpLoggingInterceptor()
		loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

		val networkInterceptor = LiveNetworkMonitorInterceptor(context)

		OkHttpClient
			.Builder()
			.addInterceptor(loggingInterceptor)
			.addInterceptor(networkInterceptor)
			.build()
	}

	@OptIn(ExperimentalSerializationApi::class)
	suspend fun downloadFile(): List<Country> {
		val loggingInterceptor = HttpLoggingInterceptor()
		loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

		val client = OkHttpClient
			.Builder()
			.addInterceptor(loggingInterceptor)
			.build()

		val request = Request
			.Builder()
			.url(Constants.URL)
			.build()

		return withContext(Dispatchers.IO) {
			try {
				val response = client.newCall(request).execute()
				Log.e("RetrofitModule", response.toString())
				response.body?.byteStream()?.let {
					Json.decodeFromStream<List<Country>>(it)
				} ?: listOf()
			} catch (e: IOException) {
				e.printStackTrace()
				listOf()
			}
		}
	}

	val retrofit: Retrofit by lazy {
		val contentType = Constants.CONTENT_TYPE_JSON.toMediaType()

		Retrofit
			.Builder()
			//.baseUrl(Constants.URL)
			.client(okHttpClient)
			.addConverterFactory(Json.asConverterFactory(contentType))
			.build()
	}
}