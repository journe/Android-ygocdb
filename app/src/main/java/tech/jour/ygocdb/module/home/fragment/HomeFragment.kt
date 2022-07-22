package tech.jour.ygocdb.module.home.fragment

import androidx.fragment.app.viewModels
import com.tcqq.searchview.Search
import tech.jour.ygocdb.base.mvvm.vm.EmptyViewModel
import tech.jour.ygocdb.common.ui.BaseFragment
import tech.jour.ygocdb.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment<FragmentHomeBinding, EmptyViewModel>() {
    override val mViewModel: EmptyViewModel by viewModels()
    override fun FragmentHomeBinding.initView() {
    }

    override fun initObserve() {
    }

    override fun initRequestData() {
    }

}