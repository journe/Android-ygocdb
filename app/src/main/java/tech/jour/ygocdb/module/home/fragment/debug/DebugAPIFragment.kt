package tech.jour.ygocdb.module.home.fragment.debug

import androidx.fragment.app.activityViewModels
import tech.jour.ygocdb.base.ktx.clickDelay
import tech.jour.ygocdb.base.ktx.d
import tech.jour.ygocdb.base.ktx.observeLiveData
import tech.jour.ygocdb.common.ui.BaseFragment
import tech.jour.ygocdb.databinding.FragmentDebugApiBinding
import tech.jour.ygocdb.module.home.activity.MainViewModel
import kotlin.getValue

class DebugAPIFragment : BaseFragment<FragmentDebugApiBinding, MainViewModel>() {

	override val mViewModel: MainViewModel by activityViewModels()

	override fun FragmentDebugApiBinding.initView() {
		button3.clickDelay {
			mViewModel.getCardDetail(48452496)
		}
	}

	override fun initObserve() {
		observeLiveData(mViewModel.cardDetailJsoupBean) {
			it.d()
		}

	}

	override fun initRequestData() {
	}

}