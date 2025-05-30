package tech.jour.ygocdb.module.home.fragment.card.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.jour.ygocdb.databinding.ItemCardDescBinding
import tech.jour.ygocdb.module.Constant

class CardDescAdapter() :
	RecyclerView.Adapter<CardDescAdapter.ViewHolder>() {
	override fun onCreateViewHolder(
		parent: ViewGroup,
		viewType: Int
	): ViewHolder {
		return ViewHolder(
			ItemCardDescBinding.inflate(
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

	inner class ViewHolder(private val binding: ItemCardDescBinding) :
		RecyclerView.ViewHolder(binding.root) {
		fun bind() {
			val item = Constant.tempCardItem
			binding.apply {
				nameTv.text = item.cn_name
				jpRubyTv.text = item.jp_ruby
				jpTv.text = item.jp_name
				enTv.text = item.en_name
				cardId.text = item.id.toString()
				cardCid.text = item.cid.toString()
				types.text = item.text?.types
				desc.text = item.text?.desc
			}
		}
	}
}
