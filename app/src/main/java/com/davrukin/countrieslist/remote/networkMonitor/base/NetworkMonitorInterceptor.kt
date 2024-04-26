package com.davrukin.countrieslist.remote.networkMonitor.base

import com.davrukin.countrieslist.remote.networkMonitor.NoNetworkException
import okhttp3.Interceptor
import okhttp3.Response

abstract class NetworkMonitorInterceptor(
	private val networkMonitor: NetworkMonitor,
) : Interceptor {

	override fun intercept(chain: Interceptor.Chain): Response {
		val request = chain.request()

		if (networkMonitor.isConnected()) {
			return chain.proceed(request)
		} else {
			throw NoNetworkException()
		}
	}
}