package com.davrukin.countrieslist.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class Language(
	val code: String? = null, // not always present, so defaulted to null to allow for ignoring
	val name: String,
	val nativeName: String? = null, // not always present, so defaulted to null to allow for ignoring
	val iso639_2: String? = null, // not always present, so defaulted to null to allow for ignoring
)
