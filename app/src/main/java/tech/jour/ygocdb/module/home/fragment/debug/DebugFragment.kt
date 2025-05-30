package tech.jour.ygocdb.module.home.fragment.debug

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import tech.jour.ygocdb.R
import tech.jour.ygocdb.base.ktx.clickDelay
import tech.jour.ygocdb.base.mvvm.vm.EmptyViewModel
import tech.jour.ygocdb.common.ui.BaseFragment
import tech.jour.ygocdb.databinding.FragmentDebugBinding

class DebugFragment : BaseFragment<FragmentDebugBinding, EmptyViewModel>() {
	override val mViewModel: EmptyViewModel by viewModels()
	override fun FragmentDebugBinding.initView() {
		button.clickDelay {
			findNavController().navigate(R.id.debugAPIFragment)
		}
		button2.clickDelay {
			findNavController().navigate(R.id.cardDetailFragment)
		}
	}

	override fun initObserve() {

	}

	override fun initRequestData() {
	}

}