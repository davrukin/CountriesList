package com.davrukin.countrieslist.presentation.flagsList

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.decode.SvgDecoder
import coil.load
import com.davrukin.countrieslist.R
import com.davrukin.countrieslist.presentation.components.ErrorDialog
import com.davrukin.countrieslist.presentation.components.LoadingDialog
import com.davrukin.countrieslist.presentation.countryList.CountryListViewModel
import com.davrukin.countrieslist.remote.LoadingState
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FlagsListFragment : Fragment(R.layout.fragment_flags_list) {

	private val viewModel: CountryListViewModel by activityViewModels()

	private val loadingDialog = LoadingDialog()
	private var errorDialog = ErrorDialog(
		onOk = viewModel::resetLoadingState,
		onReload = viewModel::refreshCountriesList
	)

	override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
		super.onViewCreated(view, savedInstanceState)

		viewModel.refreshCountriesList()

		val imageView = view.findViewById<ImageView>(R.id.flag_image)

		lifecycleScope.launch {
			viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
				viewModel.uiState.collectLatest {
					when (it.loadingState) {
						is LoadingState.ERROR -> {
							//loadingDialog.cancel()
							//errorDialog.show(childFragmentManager)
						}
						is LoadingState.LOADING -> {
							//errorDialog.cancel()
							//loadingDialog.show(childFragmentManager)
						}
						is LoadingState.SUCCESS,
						is LoadingState.NONE -> {
							//loadingDialog.cancel()
							it.countries.firstOrNull()?.flag?.let { flag ->
								imageView.load(flag) {
									decoderFactory(SvgDecoder.Factory())
								}
							}
						}
					}
				}
			}
		}
	}
}