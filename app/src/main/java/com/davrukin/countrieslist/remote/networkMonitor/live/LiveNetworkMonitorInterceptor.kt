package com.davrukin.countrieslist.remote.networkMonitor.live

import android.content.Context
import com.davrukin.countrieslist.remote.networkMonitor.base.NetworkMonitorInterceptor

class LiveNetworkMonitorInterceptor(
	context: Context?,
) : NetworkMonitorInterceptor(LiveNetworkMonitor(context))