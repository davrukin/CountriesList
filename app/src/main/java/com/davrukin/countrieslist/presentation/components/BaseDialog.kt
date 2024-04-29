package com.davrukin.countrieslist.presentation.components

import android.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.davrukin.countrieslist.R

/**
 * Base class to show a dialog with a theme
 */
abstract class BaseDialog : DialogFragment() {

	/**
	 * The tag used for the dialog fragment to reference internally
	 */
	protected abstract val TAG: String

	/**
	 * The dialog instance whose showing and closing is controlled
	 */
	protected abstract var dialog: AlertDialog

	/**
	 * Whether or not the dialog variable is initialized in the subclass; in the cases here,
	 * they are lateinit and the call to close can happen before it's shown, and vice-versa
	 *
	 * @return true if initialized, false if not
	 */
	protected abstract fun isDialogInitialized(): Boolean

	/**
	 * Closes the dialog
	 */
	fun cancel() {
		// https://www.geeksforgeeks.org/how-to-check-if-a-lateinit-variable-has-been-initialized-or-not-in-kotlin/
		if (isDialogInitialized()) {
			dialog.cancel()
		}
	}

	/**
	 * Shows the dialog with a given [FragmentManager]
	 *
	 * @param manager the fragment manager
	 */
	fun show(manager: FragmentManager) {
		show(manager, TAG)
	}

	override fun getTheme(): Int {
		return R.style.RoundedCornersDialog
	}
}