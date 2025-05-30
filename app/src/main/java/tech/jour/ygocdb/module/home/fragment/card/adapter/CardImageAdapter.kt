package tech.jour.ygocdb.module.home.fragment.card.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import tech.jour.ygocdb.databinding.ItemCardImageBinding
import tech.jour.ygocdb.model.cardUrlBig
import tech.jour.ygocdb.module.Constant

class CardImageAdapter() :
	RecyclerView.Adapter<CardImageAdapter.ViewHolder>() {
	override fun onCreateViewHolder(
		parent: ViewGroup,
		viewType: Int
	): ViewHolder {
		return ViewHolder(
			ItemCardImageBinding.inflate(
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

	inner class ViewHolder(private val binding: ItemCardImageBinding) :
		RecyclerView.ViewHolder(binding.root) {
		fun bind() {
			binding.cardIv.load(Constant.tempCardItem.cardUrlBig())

		}
	}
}
