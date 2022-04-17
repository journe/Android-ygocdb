package tech.jour.ygocdb.module.home.fragment

/**
 * Created by journey on 2022/4/16.
 */
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.RecyclerView
import coil.load
import tech.jour.ygocdb.databinding.FragmentSearchListItemBinding
import tech.jour.ygocdb.model.CardResult
import tech.jour.ygocdb.model.cardUrl

class SearchResultAdapter :
	PagingDataAdapter<CardResult, SearchResultAdapter.ViewHolder>(
		DIFF_CALLBACK
	) {

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		return ViewHolder(
			FragmentSearchListItemBinding.inflate(
				LayoutInflater.from(parent.context),
				parent,
				false
			)
		)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val item = getItem(position)!!
		holder.bind(item)

		with(holder.itemView) {
			tag = item
		}
	}


	inner class ViewHolder(private val binding: FragmentSearchListItemBinding) :
		RecyclerView.ViewHolder(binding.root) {
		fun bind(item: CardResult) {
			binding.apply {
				cardCnNameTv.text = item.cn_name
				cardJpNameTv.text = item.jp_name
				cardEnNameTv.text = item.en_name
				cardId.text = item.id.toString()
				cardCid.text = item.cid.toString()
				cardIv.load(item.cardUrl())
			}
		}
	}

	companion object {
		private val DIFF_CALLBACK = object : ItemCallback<CardResult>() {
			override fun areItemsTheSame(
				oldItem: CardResult,
				newItem: CardResult
			): Boolean {
				return oldItem.id == newItem.id
			}

			override fun areContentsTheSame(
				oldItem: CardResult,
				newItem: CardResult
			): Boolean {
				return oldItem.id == newItem.id
			}
		}
	}
}