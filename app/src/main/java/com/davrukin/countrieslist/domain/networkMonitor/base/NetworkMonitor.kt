package com.davrukin.countrieslist.domain.networkMonitor.base

interface NetworkMonitor {
	fun isConnected(): Boolean
}