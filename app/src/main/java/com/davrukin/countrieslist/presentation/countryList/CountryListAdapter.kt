package com.davrukin.countrieslist.presentation.countryList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.davrukin.countrieslist.R
import com.davrukin.countrieslist.data.Country

class CountryListAdapter(
	private val countries: List<Country>,
) : RecyclerView.Adapter<CountryListAdapter.ViewHolder>() {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view = LayoutInflater
			.from(parent.context)
			.inflate(R.layout.item_country, parent, false)

		return ViewHolder(view)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val country = countries[position]

		holder.run {
			nameRegion.text = itemView.context.getString(R.string.name_region, country.name, country.region)
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