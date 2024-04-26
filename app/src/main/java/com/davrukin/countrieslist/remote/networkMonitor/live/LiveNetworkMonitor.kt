package com.davrukin.countrieslist.remote.networkMonitor.live

import android.content.Context
import android.net.ConnectivityManager
import com.davrukin.countrieslist.remote.networkMonitor.base.NetworkMonitor

class LiveNetworkMonitor(
	context: Context,
) : NetworkMonitor {

	private val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

	override fun isConnected(): Boolean {
		return connectivityManager.activeNetwork != null
	}
}