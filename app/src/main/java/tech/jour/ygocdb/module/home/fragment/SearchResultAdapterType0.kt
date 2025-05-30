package tech.jour.ygocdb.module.home.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.util.SmartGlideImageLoader
import tech.jour.ygocdb.R
import tech.jour.ygocdb.base.ktx.clickDelay
import tech.jour.ygocdb.databinding.FragmentSearchListItemBinding
import tech.jour.ygocdb.model.CardResult
import tech.jour.ygocdb.model.SettingBean
import tech.jour.ygocdb.model.cardUrl
import tech.jour.ygocdb.model.cardUrlBig
import tech.jour.ygocdb.module.Constant
import tech.jour.ygocdb.module.home.CardDetailAttachPopup
import tech.jour.ygocdb.module.settingLiveData


class SearchResultAdapterType0 : SearchResultAdapter() {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		return ViewHolder(
			FragmentSearchListItemBinding.inflate(
				LayoutInflater.from(parent.context),
				parent,
				false
			)
		)
	}

	override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
		val item = getItem(position)!!
		holder as ViewHolder
		holder.bind(item)
	}

	inner class ViewHolder(private val binding: FragmentSearchListItemBinding) :
		RecyclerView.ViewHolder(binding.root) {
		fun bind(item: CardResult) {
			val onClickListener = View.OnClickListener {
				XPopup.Builder(binding.root.context)
					.isDestroyOnDismiss(true)
					.atView(binding.nameTv)
					.hasShadowBg(true) // 半透明背景
					.asCustom(CardDetailAttachPopup(binding.root.context, item.text!!))
					.show()
			}
			binding.apply {
				when (settingLiveData.value?.cardNameType) {
					is SettingBean.CardNameType.CNOCG -> {
						nameTv.text = item.cnocg_n
						jpRubyTv.text = item.sc_name
					}

					is SettingBean.CardNameType.Cn -> {
						nameTv.text = item.sc_name
						jpRubyTv.text = item.cn_name
					}

					is SettingBean.CardNameType.MD -> {
						nameTv.text = item.md_name
						jpRubyTv.text = item.cn_name
					}

					is SettingBean.CardNameType.NWBBS -> {
						nameTv.text = item.nwbbs_n
						jpRubyTv.text = item.sc_name
					}

					is SettingBean.CardNameType.YGOPro -> {
						nameTv.text = item.cn_name
						jpRubyTv.text = item.sc_name
					}

					else -> {
						nameTv.text = item.sc_name
						jpRubyTv.text = item.cn_name
					}
				}

				jpTv.text = item.jp_name
				enTv.text = item.en_name
				cardCLickView.setOnClickListener(onClickListener)
				cardId.text = item.id.toString()
				cardCid.text = item.cid.toString()
				cardIv.load(item.cardUrl())
				cardIv.setOnClickListener {
					XPopup.Builder(it.context)
						.asImageViewer(cardIv, item.cardUrlBig(), SmartGlideImageLoader())
						.show()
				}
				detailBtn.clickDelay {
					Constant.tempCardItem = item
					root.findNavController().navigate(R.id.cardDetailFragment)
				}
			}
		}
	}
}