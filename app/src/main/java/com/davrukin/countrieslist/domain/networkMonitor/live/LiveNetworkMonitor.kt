package com.davrukin.countrieslist.domain.networkMonitor.live

import android.content.Context
import android.net.ConnectivityManager
import com.davrukin.countrieslist.domain.networkMonitor.base.NetworkMonitor

/**
 * Concrete implementation to check for network connectivity status
 *
 * @constructor
 * Creates a new instance of this class and gets the [Context.CONNECTIVITY_SERVICE] to check for network status
 *
 * @param context the application context which gets the [ConnectivityManager] service internally. If context is null, this reports a live connection
 */
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