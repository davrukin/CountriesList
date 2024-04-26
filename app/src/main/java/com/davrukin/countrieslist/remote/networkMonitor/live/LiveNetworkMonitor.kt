package com.davrukin.countrieslist.remote.networkMonitor.live

import android.content.Context
import android.net.ConnectivityManager
import com.davrukin.countrieslist.remote.networkMonitor.base.NetworkMonitor

class LiveNetworkMonitor(
	context: Context?,
) : NetworkMonitor {

	private val connectivityManager = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager

	override fun isConnected(): Boolean {
		return if (connectivityManager != null) {
			connectivityManager.activeNetwork != null
		} else {
			true
			// might not be the best to assume there is a connection when context is null
			// however, it would probably be a good idea to see in which cases context could be null
			// and determine how that reflect upon this case of network connectivity
		}
	}
}