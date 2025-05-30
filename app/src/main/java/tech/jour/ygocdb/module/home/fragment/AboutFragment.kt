package tech.jour.ygocdb.module.home.fragment

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import tech.jour.ygocdb.BuildConfig
import tech.jour.ygocdb.R
import tech.jour.ygocdb.base.ktx.clickDelay
import tech.jour.ygocdb.base.ktx.gone
import tech.jour.ygocdb.base.ktx.visible
import tech.jour.ygocdb.base.mvvm.vm.EmptyViewModel
import tech.jour.ygocdb.common.ui.BaseFragment
import tech.jour.ygocdb.databinding.FragmentAboutBinding

class AboutFragment : BaseFragment<FragmentAboutBinding, EmptyViewModel>() {
	override val mViewModel: EmptyViewModel by viewModels()
	override fun FragmentAboutBinding.initView() {
		if (BuildConfig.DEBUG) debugBtn.visible() else debugBtn.gone()
		debugBtn.clickDelay {
			findNavController().navigate(R.id.debugFragment)
		}
	}

	override fun initObserve() {

	}

	override fun initRequestData() {
	}

}