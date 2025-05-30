package tech.jour.ygocdb.module.home.fragment

import android.view.LayoutInflater
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
import tech.jour.ygocdb.module.Constant
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
			binding.apply {
				when (settingLiveData.value?.cardNameType) {
					is SettingBean.CardNameType.CNOCG -> {
						nameTv.text = item.cnocg_n
					}

					is SettingBean.CardNameType.Cn -> {
						nameTv.text = item.sc_name
					}

					is SettingBean.CardNameType.MD -> {
						nameTv.text = item.md_name
					}

					is SettingBean.CardNameType.NWBBS -> {
						nameTv.text = item.nwbbs_n
					}

					is SettingBean.CardNameType.YGOPro -> {
						nameTv.text = item.cn_name
					}

					else -> {
						nameTv.text = item.cn_name
					}
				}

				enTv.text = item.en_name
				cardIv.load(item.cardUrlBig())
				cardIv.setOnClickListener {
					XPopup.Builder(it.context)
						.asImageViewer(cardIv, item.cardUrlBig(), SmartGlideImageLoader())
						.show()
				}
				root.clickDelay {
					Constant.tempCardItem = item
					root.findNavController().navigate(R.id.cardDetailFragment)
				}
			}
		}
	}

}