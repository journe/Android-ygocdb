package tech.jour.ygocdb.module.home.fragment

import android.content.Context
import android.view.KeyEvent
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.PagingData
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BasePopupView
import com.lxj.xpopup.impl.FullScreenPopupView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tech.jour.ygocdb.R
import tech.jour.ygocdb.base.ktx.clickDelay
import tech.jour.ygocdb.base.ktx.d
import tech.jour.ygocdb.base.ktx.gone
import tech.jour.ygocdb.base.ktx.observeLiveData
import tech.jour.ygocdb.base.ktx.visible
import tech.jour.ygocdb.common.ui.BaseFragment
import tech.jour.ygocdb.databinding.FragmentHomeBinding
import tech.jour.ygocdb.databinding.PopSearchBinding
import tech.jour.ygocdb.model.CardResult
import tech.jour.ygocdb.model.SettingBean
import tech.jour.ygocdb.module.home.activity.MainViewModel
import tech.jour.ygocdb.module.home.activity.SearchHistoryAdapter
import tech.jour.ygocdb.module.settingLiveData

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding, MainViewModel>() {
	override val mViewModel: MainViewModel by activityViewModels()

	private var searchResultAdapter = SearchResultAdapter()

	private lateinit var searchPopView: BasePopupView

	override fun FragmentHomeBinding.initView() {

		searchPopView = XPopup.Builder(requireContext())
			.isDestroyOnDismiss(false) //对于只使用一次的弹窗，推荐设置这个
			.autoOpenSoftInput(false)
			.hasShadowBg(false)
			.asCustom(SearchPopView(requireContext()))

		searchFab.clickDelay {
			searchPopView.show()
		}
	}

	private fun submitSearchQuery(query: String) {
		mViewModel.submitSearch(query)
		searchPopView.dismiss()
	}

	override fun initObserve() {
		mViewModel.showEmptyView.observe(this) {
			if (it) mBinding.emptyViw.visible()
			else mBinding.emptyViw.gone()
		}

		settingLiveData.observe(this) {
			it.d()
			when (it.cardListType) {
				is SettingBean.CardListType.SingleList -> {
					searchResultAdapter = SearchResultAdapterType0()
					mBinding.searchResultRv.apply {
					}
				}

				is SettingBean.CardListType.DoubleList -> {
					searchResultAdapter = SearchResultAdapterType1()
					mBinding.searchResultRv.apply {
						layoutManager = StaggeredGridLayoutManager(2, RecyclerView.VERTICAL)
					}
				}
			}
			mBinding.searchResultRv.adapter = searchResultAdapter
			observeLiveData(mViewModel.searchResult, ::processData)
		}

	}

	override fun initRequestData() {

	}

	private fun processData(data: PagingData<CardResult>) {
		lifecycleScope.launch {
			searchResultAdapter.submitData(data)
		}
	}

	private fun getHistorySearchItem(searchHistoryRv: RecyclerView) {
		lifecycleScope.launch {
			mViewModel.getRecentList().collectLatest {
				if (it != null) {
					searchHistoryRv.adapter = SearchHistoryAdapter(it) { query ->
						submitSearchQuery(query)
					}
				}
			}
		}
	}

	inner class SearchPopView(context: Context) : FullScreenPopupView(context) {

		override fun getImplLayoutId(): Int {
			return R.layout.pop_search
		}

		override fun onCreate() {
			super.onCreate()
			val binding = PopSearchBinding.bind(this.popupImplView)
			getHistorySearchItem(binding.searchHistoryRv)
			binding.apply {
				searchView.editText
					.setOnEditorActionListener { v: TextView?, actionId: Int, event: KeyEvent? ->
						searchView.hide()
						submitSearchQuery(
							searchView.text.toString()
						)
						false
					}
			}
		}
	}
}