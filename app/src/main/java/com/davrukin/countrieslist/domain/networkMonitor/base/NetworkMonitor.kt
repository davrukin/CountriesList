package com.davrukin.countrieslist.domain.networkMonitor.base

/**
 * Interface with functions to describe network conditions
 */
interface NetworkMonitor {
	/**
	 * Function to return to app if there is a network connection
	 *
	 * @return true if connected, false if not
	 */
	fun isConnected(): Boolean
}