package tech.jour.ygocdb.module.home.activity

/**
 * Created by journey on 2024/2/2.
 */

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import tech.jour.ygocdb.databinding.FragmentSearchHistoryItemBinding
import tech.jour.ygocdb.module.home.database.SearchHistoryBean

class SearchHistoryAdapter(
	private val data: List<SearchHistoryBean>, private val onClickListener: (String) -> Unit
) : RecyclerView.Adapter<SearchHistoryAdapter.ViewHolder>() {
	override fun onCreateViewHolder(
		parent: ViewGroup, viewType: Int
	): ViewHolder {
		return ViewHolder(
			FragmentSearchHistoryItemBinding.inflate(
				LayoutInflater.from(parent.context), parent, false
			)
		)
	}

	override fun onBindViewHolder(
		holder: ViewHolder, position: Int
	) {
		holder.bind(data[position])
	}

	override fun getItemCount(): Int {
		return data.size
	}

	inner class ViewHolder(private val binding: FragmentSearchHistoryItemBinding) :
		RecyclerView.ViewHolder(binding.root) {
		fun bind(bean: SearchHistoryBean) {
			binding.searchbarSuggestionTitle.text = bean.query
			binding.root.setOnClickListener {
				onClickListener(bean.query)
			}
		}
	}
}
