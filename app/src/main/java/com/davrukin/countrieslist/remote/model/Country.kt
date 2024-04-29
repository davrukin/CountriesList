package com.davrukin.countrieslist.remote.model

import kotlinx.serialization.Serializable


@Serializable
data class Country(
	val capital: String,
	val code: String,
	val currency: Currency,
	val flag: String,
	val language: Language,
	val name: String,
	val region: String,
	val demonym: String? = null, // not always present, so defaulted to null to allow for ignoring
)
