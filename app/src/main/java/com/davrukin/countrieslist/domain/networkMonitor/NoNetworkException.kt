package com.davrukin.countrieslist.domain.networkMonitor

import androidx.annotation.StringRes
import com.davrukin.countrieslist.R
import java.io.IOException

class NoNetworkException : IOException() {
	@StringRes val messageId: Int = R.string.error_no_network
}