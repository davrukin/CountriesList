package com.davrukin.countrieslist.presentation.components

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import com.davrukin.countrieslist.R

class LoadingDialog : BaseDialog() {

	override val TAG: String = "LoadingDialog"

	override lateinit var dialog: AlertDialog

	override fun isDialogInitialized(): Boolean {
		return ::dialog.isInitialized
	}

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
}