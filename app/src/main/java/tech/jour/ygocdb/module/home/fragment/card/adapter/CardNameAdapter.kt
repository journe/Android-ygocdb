package tech.jour.ygocdb.module.home.fragment.card.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import tech.jour.ygocdb.databinding.ItemCardNameAvailBinding
import tech.jour.ygocdb.databinding.ItemCardNameBinding
import tech.jour.ygocdb.model.CardDetailJsoupBean
import tech.jour.ygocdb.model.CardDetailJsoupBean.AvailPack
import tech.jour.ygocdb.module.Constant

class CardNameAdapter(private val bean: CardDetailJsoupBean) :
	RecyclerView.Adapter<CardNameAdapter.ViewHolder>() {
	override fun onCreateViewHolder(
		parent: ViewGroup,
		viewType: Int
	): ViewHolder {
		return ViewHolder(
			ItemCardNameBinding.inflate(
				LayoutInflater.from(parent.context),
				parent,
				false
			)
		)
	}

	override fun onBindViewHolder(
		holder: ViewHolder,
		position: Int
	) {
		holder.bind()
	}

	override fun getItemCount(): Int {
		return 1
	}

	inner class ViewHolder(private val binding: ItemCardNameBinding) :
		RecyclerView.ViewHolder(binding.root) {
		fun bind() {
			val item = Constant.tempCardItem
			binding.apply {
				scnameTv.text = "简中官方    " + item.sc_name
				mdnameTv.text = "大师决斗    " + item.md_name
				nwnameTv.text = "NWBBS     " + item.nwbbs_n
				ocgnameTv.text = "CNOCG     " + item.cnocg_n
				ygonameTv.text = "YGOPro     " + item.cn_name
				jpTv.text = "日文          " + item.jp_name
				enTv.text = "英文          " + item.en_name

				if (bean.avail != null) {
					availRv.adapter = CardNameAvailAdapter(bean.avail!!)
				}
				bean.packs?.forEach { itemBean ->
					val chip = Chip(binding.root.context).apply {
						text = "${itemBean.date}  ${itemBean.code}  ${itemBean.name}"
						setOnClickListener {
							itemBean.code
						}
					}
					chipGroup.addView(chip)
				}

			}
		}
	}

	inner class CardNameAvailAdapter(private val avail: List<AvailPack>) :
		RecyclerView.Adapter<CardNameAvailAdapter.ViewHolder>() {
		override fun onCreateViewHolder(
			parent: ViewGroup,
			viewType: Int
		): ViewHolder {
			return ViewHolder(
				ItemCardNameAvailBinding.inflate(
					LayoutInflater.from(parent.context),
					parent,
					false
				)
			)
		}

		override fun onBindViewHolder(
			holder: ViewHolder,
			position: Int
		) {
			holder.bind(avail[position])
		}

		override fun getItemCount(): Int {
			return avail.size
		}

		inner class ViewHolder(private val binding: ItemCardNameAvailBinding) :
			RecyclerView.ViewHolder(binding.root) {
			fun bind(avail: AvailPack) {
				binding.apply {
					tagTv.text = avail.tag
					nameTv.text = avail.name
				}
			}
		}
	}
}
