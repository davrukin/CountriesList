package com.davrukin.countrieslist.domain.networkMonitor

import androidx.annotation.StringRes
import com.davrukin.countrieslist.R
import java.io.IOException

/**
 * A custom exception thrown when there is no network via [com.davrukin.countrieslist.domain.networkMonitor.base.NetworkMonitorInterceptor]
 */
class NoNetworkException : IOException() {
	@StringRes val messageId: Int = R.string.error_no_network
}