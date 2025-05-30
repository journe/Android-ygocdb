package tech.jour.ygocdb.module.home.fragment.card.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.jour.ygocdb.databinding.ItemCardSupplementBinding
import tech.jour.ygocdb.databinding.ItemCardSupplementItemBinding
import tech.jour.ygocdb.model.CardDetailJsoupBean

class CardSupplementAdapter(private val bean: CardDetailJsoupBean) :
	RecyclerView.Adapter<CardSupplementAdapter.ViewHolder>() {
	override fun onCreateViewHolder(
		parent: ViewGroup,
		viewType: Int
	): ViewHolder {
		return ViewHolder(
			ItemCardSupplementBinding.inflate(
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

	inner class ViewHolder(private val binding: ItemCardSupplementBinding) :
		RecyclerView.ViewHolder(binding.root) {
		fun bind() {
			binding.apply {
				if (bean.supplements.isNotEmpty()) {
					availRv.adapter = CardSupplementItemAdapter(bean.supplements)
				}

			}
		}
	}

	inner class CardSupplementItemAdapter(private val bean: List<String>) :
		RecyclerView.Adapter<CardSupplementItemAdapter.ViewHolder>() {
		override fun onCreateViewHolder(
			parent: ViewGroup,
			viewType: Int
		): ViewHolder {
			return ViewHolder(
				ItemCardSupplementItemBinding.inflate(
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
			holder.bind(bean[position])
		}

		override fun getItemCount(): Int {
			return bean.size
		}

		inner class ViewHolder(private val binding: ItemCardSupplementItemBinding) :
			RecyclerView.ViewHolder(binding.root) {
			fun bind(string: String) {
				binding.apply {
					textView.text = string
				}
			}
		}
	}

}
