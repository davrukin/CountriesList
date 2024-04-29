package com.davrukin.countrieslist.domain.networkMonitor.live

import android.content.Context
import com.davrukin.countrieslist.domain.networkMonitor.NoNetworkException
import com.davrukin.countrieslist.domain.networkMonitor.base.NetworkMonitorInterceptor

/**
 * Concrete implementation of the network monitor interceptor
 *
 * @constructor
 * Creates a new instance of this class
 *
 * @param context used to communicate with the connectivity service. If it is null, this will report a live connection
 */
class LiveNetworkMonitorInterceptor(
	context: Context?,
) : NetworkMonitorInterceptor(
	networkMonitor = LiveNetworkMonitor(context),
	errorMessage = context?.getString(NoNetworkException().messageId) ?: "",
	// not the best way to get the localized error message since the exception
	// isn't actually being thrown. if Retrofit were being used, then the
	// exception otherwise thrown in the interceptor would propagate and be accessible to catch
)