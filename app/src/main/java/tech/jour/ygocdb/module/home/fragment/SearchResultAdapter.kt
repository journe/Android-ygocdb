package tech.jour.ygocdb.module.home.fragment

/**
 * Created by journey on 2022/4/16.
 */
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.RecyclerView
import tech.jour.ygocdb.databinding.FragmentSearchListItemBinding
import tech.jour.ygocdb.model.CardResult

open class SearchResultAdapter :
	PagingDataAdapter<CardResult, RecyclerView.ViewHolder>(
		DIFF_CALLBACK
	) {

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
	}

	inner class ViewHolder(private val binding: FragmentSearchListItemBinding) :
		RecyclerView.ViewHolder(binding.root) {
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