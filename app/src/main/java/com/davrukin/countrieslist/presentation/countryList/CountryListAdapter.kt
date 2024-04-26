package com.davrukin.countrieslist.presentation.countryList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.davrukin.countrieslist.R
import com.davrukin.countrieslist.domain.model.CountryInfo

class CountryListAdapter(
	private var countries: List<CountryInfo>,
) : RecyclerView.Adapter<CountryListAdapter.ViewHolder>() {

	fun updateCountries(newCountries: List<CountryInfo>) {
		val diffCallback = CountryListCallback(countries, newCountries)
		val diffCountries = DiffUtil.calculateDiff(diffCallback)
		countries = newCountries
		diffCountries.dispatchUpdatesTo(this)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view = LayoutInflater
			.from(parent.context)
			.inflate(R.layout.item_country, parent, false)

		return ViewHolder(view)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val country = countries[position]

		holder.run {
			nameRegion.text = if (country.region.isNotBlank()) {
				itemView.context.getString(R.string.name_region, country.name, country.region)
			} else {
				country.name
			}
			code.text = country.code
			capital.text = country.capital
		}
	}

	override fun getItemCount(): Int {
		return countries.size
	}

	class ViewHolder(
		itemView: View,
	) : RecyclerView.ViewHolder(itemView) {
		val nameRegion: TextView = itemView.findViewById(R.id.text_countryNameRegion)
		val code: TextView = itemView.findViewById(R.id.text_countryCode)
		val capital: TextView = itemView.findViewById(R.id.text_countryCapital)
	}
}