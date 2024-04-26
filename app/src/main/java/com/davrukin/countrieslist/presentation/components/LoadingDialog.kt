package com.davrukin.countrieslist.presentation.components

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.davrukin.countrieslist.R

class LoadingDialog : DialogFragment() {

	private lateinit var dialog: AlertDialog

	override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
		dialog = AlertDialog
			.Builder(context)
			//.setMessage(R.string.loading)
			.setCancelable(false)
			.setView(R.layout.dialog_progress)
			.create()

		// https://stackoverflow.com/a/55140517
		dialog.window?.setBackgroundDrawableResource(R.drawable.dialog_rounded_background)

		return dialog
	}

	fun cancel() {
		dialog.cancel()
	}

	fun show(manager: FragmentManager) {
		show(manager, TAG)
	}

	override fun getTheme(): Int {
		return R.style.RoundedCornersDialog
	}

	companion object {
		const val TAG: String = "LoadingDialog"
	}
}