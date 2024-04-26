package com.davrukin.countrieslist.presentation.countryList

import androidx.recyclerview.widget.DiffUtil
import com.davrukin.countrieslist.domain.model.CountryInfo

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

	override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
		return super.getChangePayload(oldItemPosition, newItemPosition)
	}
}