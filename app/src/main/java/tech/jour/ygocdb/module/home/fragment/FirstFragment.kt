package tech.jour.ygocdb.module.home.fragment

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.PagingData
import dagger.hilt.android.AndroidEntryPoint
import tech.jour.ygocdb.R
import tech.jour.ygocdb.base.ktx.observeLiveData
import tech.jour.ygocdb.common.ui.BaseFragment
import tech.jour.ygocdb.databinding.FragmentFirstBinding
import tech.jour.ygocdb.model.CardResult
import tech.jour.ygocdb.module.home.activity.HomeViewModel

@AndroidEntryPoint
class FirstFragment : BaseFragment<FragmentFirstBinding, HomeViewModel>() {

	override val mViewModel: HomeViewModel by activityViewModels()

	private val searchResultAdapter = SearchResultAdapter()

	override fun FragmentFirstBinding.initView() {
//		mBinding.buttonFirst.setOnClickListener {
//			findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
//		}
		mBinding.searchResultRv.adapter = searchResultAdapter
	}

	override fun initObserve() {
		observeLiveData(mViewModel.searchResult, ::processData)
//		lifecycleScope.launchWhenStarted {
//			mViewModel.searchResult.collect {
//				Logger.d("mViewModel.searchResult.collect ")
//				searchResultAdapter.submitData(it)
//			}
//		}
//		225 321
	}

	override fun initRequestData() {
	}

	private fun processData(data: PagingData<CardResult>) {
		lifecycleScope.launchWhenStarted {
			searchResultAdapter.submitData(data)
		}
//		toast(data.size)
//		searchResultAdapter.submitData()
//        mBinding.vTvHello.text = data
//        mBinding.vTvHello.setTextColor(Color.BLUE)
	}
}