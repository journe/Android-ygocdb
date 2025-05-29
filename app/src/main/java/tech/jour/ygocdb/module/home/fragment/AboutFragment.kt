package tech.jour.ygocdb.module.home.fragment

import androidx.fragment.app.viewModels
import tech.jour.ygocdb.base.mvvm.vm.EmptyViewModel
import tech.jour.ygocdb.common.ui.BaseFragment
import tech.jour.ygocdb.databinding.FragmentAboutBinding

class AboutFragment : BaseFragment<FragmentAboutBinding, EmptyViewModel>() {
	override val mViewModel: EmptyViewModel by viewModels()
	override fun FragmentAboutBinding.initView() {
	}

	override fun initObserve() {

	}

	override fun initRequestData() {
	}

}