package com.davrukin.countrieslist.domain.networkMonitor.base

import com.davrukin.countrieslist.domain.networkMonitor.NoNetworkException
import okhttp3.Interceptor
import okhttp3.Response

/**
 * An interceptor used by OkHttp requests to check for network conditions on each request
 *
 * @property networkMonitor an instance of the network monitor which is used to check if there's a network connection
 */
abstract class NetworkMonitorInterceptor(
	private val networkMonitor: NetworkMonitor,
	private val errorMessage: String,
) : Interceptor {

	// https://stackoverflow.com/a/65469952
	override fun intercept(chain: Interceptor.Chain): Response {
		val request = chain.request()

		return if (networkMonitor.isConnected()) {
			chain.proceed(request)
		} else {
			Response
				.Builder()
				.request(request)
				.message(errorMessage)
				.build()
			// throw NoNetworkException()
			// ideally would "throw NoNetworkException()" with Retrofit
		}
	}
}