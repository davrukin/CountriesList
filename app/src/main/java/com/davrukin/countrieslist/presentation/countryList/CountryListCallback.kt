package com.davrukin.countrieslist.presentation.countryList

import androidx.recyclerview.widget.DiffUtil
import com.davrukin.countrieslist.domain.model.CountryInfo

// https://www.geeksforgeeks.org/diffutil-in-recyclerview-in-android/
/**
 * Class to compare one list of countries with the next to determine changes
 *
 * @property oldList the old list of countries
 * @property newList the new list of countries
 */
class CountryListCallback(
	private val oldList: List<CountryInfo>,
	private val newList: List<CountryInfo>,
) : DiffUtil.Callback() {

	override fun getOldListSize(): Int {
		return oldList.size
	}

	override fun getNewListSize(): Int {
		return newList.size
	}

	override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
		return oldList[oldItemPosition].code === newList[newItemPosition].code
	}

	override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
		return oldList[oldItemPosition].code == newList[newItemPosition].code
	}
}