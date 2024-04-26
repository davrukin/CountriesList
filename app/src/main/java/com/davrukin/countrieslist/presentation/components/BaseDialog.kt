package com.davrukin.countrieslist.presentation.components

import android.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.davrukin.countrieslist.R

abstract class BaseDialog : DialogFragment() {

	protected abstract val TAG: String

	protected abstract var dialog: AlertDialog

	protected abstract fun isDialogInitialized(): Boolean

	fun cancel() {
		// https://www.geeksforgeeks.org/how-to-check-if-a-lateinit-variable-has-been-initialized-or-not-in-kotlin/
		if (isDialogInitialized()) {
			dialog.cancel()
		}
	}

	fun show(manager: FragmentManager) {
		show(manager, TAG)
	}

	override fun getTheme(): Int {
		return R.style.RoundedCornersDialog
	}
}