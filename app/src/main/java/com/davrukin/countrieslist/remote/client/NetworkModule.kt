package com.davrukin.countrieslist.remote.client

import android.content.Context
import android.util.Log
import com.davrukin.countrieslist.domain.data.Constants
import com.davrukin.countrieslist.domain.networkMonitor.live.LiveNetworkMonitorInterceptor
import com.davrukin.countrieslist.remote.model.Country
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import okio.IOException

internal class NetworkModule(context: Context?) {

	private val loggingInterceptor = HttpLoggingInterceptor().apply {
		level = HttpLoggingInterceptor.Level.BODY
	}

	private val networkInterceptor = LiveNetworkMonitorInterceptor(context)

	// https://towardsdev.com/android-kotlin-jetpack-compose-download-file-save-to-local-storage-and-preview-using-webview-79e5dec2338a
	private val okHttpClient: OkHttpClient by lazy {
		OkHttpClient
			.Builder()
			.addInterceptor(loggingInterceptor)
			.addInterceptor(networkInterceptor)
			.build()
	}

	@OptIn(ExperimentalSerializationApi::class)
	suspend fun downloadFile(): List<Country>? {
		val request = Request
			.Builder()
			.url(Constants.URL)
			.build()

		// https://kotlinlang.org/docs/serialization.html#serialize-and-deserialize-json
		return withContext(Dispatchers.IO) {
			try {
				val response = okHttpClient.newCall(request).execute()
				Log.v("NetworkModule", response.toString())
				response.body?.byteStream()?.let {
					Json.decodeFromStream<List<Country>>(it)
				} ?: listOf()
			} catch (e: IOException) {
				Log.e("NetworkModule", "Error decoding JSON response", e)
				null
			}
		}
	}
}