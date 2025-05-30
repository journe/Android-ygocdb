package tech.jour.ygocdb.module.home.fragment.card

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import dagger.hilt.android.AndroidEntryPoint
import tech.jour.ygocdb.base.ktx.observeLiveData
import tech.jour.ygocdb.common.ui.BaseFragment
import tech.jour.ygocdb.databinding.FragmentCardDetailBinding
import tech.jour.ygocdb.module.Constant
import tech.jour.ygocdb.module.home.activity.MainViewModel
import tech.jour.ygocdb.module.home.fragment.card.adapter.CardDescAdapter
import tech.jour.ygocdb.module.home.fragment.card.adapter.CardFAQAdapter
import tech.jour.ygocdb.module.home.fragment.card.adapter.CardImageAdapter
import tech.jour.ygocdb.module.home.fragment.card.adapter.CardNameAdapter
import tech.jour.ygocdb.module.home.fragment.card.adapter.CardSupplementAdapter


@AndroidEntryPoint
class CardDetailFragment : BaseFragment<FragmentCardDetailBinding, MainViewModel>() {

	override val mViewModel: MainViewModel by viewModels()

	private val cardDetailBean = Constant.tempCardItem

	private val concatAdapter = ConcatAdapter(CardImageAdapter(), CardDescAdapter())
	override fun FragmentCardDetailBinding.initView() {
//		cardIv.load(cardDetailBean.cardUrlBig())
//		concatAdapter.addAdapter(CardImageAdapter())
//		concatAdapter.addAdapter(CardNameAdapter())
		recyclerView.adapter = concatAdapter
//		PagerSnapHelper().attachToRecyclerView(recyclerView)
	}

	override fun initObserve() {
		observeLiveData(mViewModel.cardDetailJsoupBean) {
			if (it.cid == cardDetailBean.cid) {
				concatAdapter.addAdapter(CardNameAdapter(it))
				concatAdapter.addAdapter(CardSupplementAdapter(it))
				if (it.faqs.isNotEmpty()) {
					concatAdapter.addAdapter(CardFAQAdapter(it.faqs))
				}
			}
		}
	}

	override fun initRequestData() {
		mViewModel.getCardDetail(cardDetailBean.id!!)
	}

//	private fun processData(data: PagingData<CardResult>) {
//		lifecycleScope.launch {
//		}
//	}
}