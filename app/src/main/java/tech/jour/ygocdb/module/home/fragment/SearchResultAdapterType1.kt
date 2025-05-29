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
import tech.jour.ygocdb.databinding.FragmentSearchListItemType1Binding
import tech.jour.ygocdb.model.CardResult
import tech.jour.ygocdb.model.SettingBean
import tech.jour.ygocdb.model.cardUrlBig
import tech.jour.ygocdb.module.home.CardDetailAttachPopup
import tech.jour.ygocdb.module.settingLiveData


class SearchResultAdapterType1 : SearchResultAdapter() {
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
		return ViewHolder(
			FragmentSearchListItemType1Binding.inflate(
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

	inner class ViewHolder(private val binding: FragmentSearchListItemType1Binding) :
		RecyclerView.ViewHolder(binding.root) {
		fun bind(item: CardResult) {
			val onClickListener = View.OnClickListener {
				XPopup.Builder(binding.root.context)
					.isDestroyOnDismiss(true)
					.atView(binding.cardCnNameTv)
					.hasShadowBg(true) // 半透明背景
					.asCustom(CardDetailAttachPopup(binding.root.context, item.text))
					.show()
			}
			binding.apply {
				when (settingLiveData.value?.cardNameType) {
					is SettingBean.CardNameType.CNOCG -> {
						cardCnNameTv.text = item.cnocg_n
					}

					is SettingBean.CardNameType.Cn -> {
						cardCnNameTv.text = item.sc_name
					}

					is SettingBean.CardNameType.MD -> {
						cardCnNameTv.text = item.md_name
					}

					is SettingBean.CardNameType.NWBBS -> {
						cardCnNameTv.text = item.nwbbs_n
					}

					is SettingBean.CardNameType.YGOPro -> {
						cardCnNameTv.text = item.cn_name
					}

					else -> {
						cardCnNameTv.text = item.sc_name
					}
				}

				cardEnNameTv.text = item.en_name
//				cardCLickView.setOnClickListener(onClickListener)
//				cardId.text = item.id.toString()
//				cardCid.text = item.cid.toString()
				cardIv.load(item.cardUrlBig())
				cardIv.setOnClickListener {
					XPopup.Builder(it.context)
						.asImageViewer(cardIv, item.cardUrlBig(), SmartGlideImageLoader())
						.show()
				}
				root.clickDelay {
					root.findNavController().navigate(R.id.aboutFragment)
				}
			}
		}
	}

}