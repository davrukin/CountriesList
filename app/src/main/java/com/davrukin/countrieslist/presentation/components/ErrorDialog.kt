package com.davrukin.countrieslist.presentation.components

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.davrukin.countrieslist.R

class ErrorDialog(
	private val onOk: () -> Unit = {},
	private val onReload: () -> Unit = {},
) : DialogFragment() {

	private lateinit var dialog: AlertDialog

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

	fun cancel() {
		// https://www.geeksforgeeks.org/how-to-check-if-a-lateinit-variable-has-been-initialized-or-not-in-kotlin/
		if (::dialog.isInitialized) {
			dialog.cancel()
		}
	}

	fun show(manager: FragmentManager) {
		show(manager, TAG)
	}

	override fun getTheme(): Int {
		return R.style.RoundedCornersDialog
	}

	companion object {
		const val TAG: String = "ErrorDialog"
	}
}