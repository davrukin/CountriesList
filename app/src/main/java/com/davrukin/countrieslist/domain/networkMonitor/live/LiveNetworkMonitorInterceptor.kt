package com.davrukin.countrieslist.domain.networkMonitor.live

import android.content.Context
import com.davrukin.countrieslist.domain.networkMonitor.base.NetworkMonitorInterceptor

class LiveNetworkMonitorInterceptor(
	context: Context?,
) : NetworkMonitorInterceptor(LiveNetworkMonitor(context))