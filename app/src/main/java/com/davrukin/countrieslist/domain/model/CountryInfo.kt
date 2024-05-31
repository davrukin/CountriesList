package com.davrukin.countrieslist.domain.model

/**
 *
 * Model class for what is displayed in each RecyclerView element
 *
 * @property code the country code, two characters
 * @property region the region the country is in, two characters
 * @property name the name of the country
 * @property capital the capital city of the country
 */
data class CountryInfo(
	val code: String,
	val region: String,
	val name: String,
	val capital: String,
	val flag: String,
)
