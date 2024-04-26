package com.davrukin.countrieslist.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class Language(
	val code: String? = null,
	val name: String,
	val nativeName: String? = null,
	val iso639_2: String? = null,
)
