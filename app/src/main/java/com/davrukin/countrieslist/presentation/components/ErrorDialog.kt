package com.davrukin.countrieslist.presentation.components

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import com.davrukin.countrieslist.R

class ErrorDialog(
	private val onOk: () -> Unit = {},
	private val onReload: () -> Unit = {},
) : BaseDialog() {

	override val TAG: String = "ErrorDialog"

	override lateinit var dialog: AlertDialog

	override fun isDialogInitialized(): Boolean {
		return ::dialog.isInitialized
	}

	override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
		dialog = AlertDialog
			.Builder(context)
			.setTitle(R.string.error_title)
			.setMessage(R.string.error_message)
			.setNegativeButton(R.string.ok) { _, _ ->
				cancel()
				onOk.invoke()
			}
			.setPositiveButton(R.string.try_again) { _, _ ->
				cancel()
				onReload.invoke()
			}
			.setCancelable(false)
			.create()

		return dialog
	}
}