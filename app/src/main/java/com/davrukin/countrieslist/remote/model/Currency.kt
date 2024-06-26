package com.davrukin.countrieslist.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class Currency(
	val code: String,
	val name: String,
	val symbol: String?, // this can be null sometimes, but it always exists
)
