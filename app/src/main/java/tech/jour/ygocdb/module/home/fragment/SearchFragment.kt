package tech.jour.ygocdb.module.home.fragment

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import dagger.hilt.android.AndroidEntryPoint
import tech.jour.ygocdb.base.ktx.observeLiveData
import tech.jour.ygocdb.common.ui.BaseFragment
import tech.jour.ygocdb.databinding.FragmentSearchBinding
import tech.jour.ygocdb.model.CardResult
import tech.jour.ygocdb.module.home.activity.MainViewModel

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding, MainViewModel>() {

	override val mViewModel: MainViewModel by activityViewModels()

	private val searchResultAdapter = SearchResultAdapter()

	override fun FragmentSearchBinding.initView() {
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
	}
}