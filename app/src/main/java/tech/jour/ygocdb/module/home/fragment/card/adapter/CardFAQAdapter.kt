package tech.jour.ygocdb.module.home.fragment.card.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lxj.xpopup.XPopup
import com.lxj.xpopup.core.BottomPopupView
import tech.jour.ygocdb.R
import tech.jour.ygocdb.base.ktx.clickDelay
import tech.jour.ygocdb.databinding.ItemCardFaqBinding
import tech.jour.ygocdb.databinding.ItemCardFaqItemBinding
import tech.jour.ygocdb.databinding.PopFaqBinding
import tech.jour.ygocdb.model.CardDetailJsoupBean.FaqData

class CardFAQAdapter(private val beans: List<FaqData>) :
	RecyclerView.Adapter<CardFAQAdapter.ViewHolder>() {
	override fun onCreateViewHolder(
		parent: ViewGroup,
		viewType: Int
	): ViewHolder {
		return ViewHolder(
			ItemCardFaqBinding.inflate(
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

	inner class ViewHolder(private val binding: ItemCardFaqBinding) :
		RecyclerView.ViewHolder(binding.root) {
		fun bind() {
			binding.apply {
				faqRv.adapter = CardFaqItemAdapter(beans)

			}
		}
	}

	inner class CardFaqItemAdapter(private val beans: List<FaqData>) :
		RecyclerView.Adapter<CardFaqItemAdapter.ViewHolder>() {
		override fun onCreateViewHolder(
			parent: ViewGroup,
			viewType: Int
		): ViewHolder {
			return ViewHolder(
				ItemCardFaqItemBinding.inflate(
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
			holder.bind(this@CardFaqItemAdapter.beans[position])
		}

		override fun getItemCount(): Int {
			return beans.size
		}

		inner class ViewHolder(private val binding: ItemCardFaqItemBinding) :
			RecyclerView.ViewHolder(binding.root) {
			fun bind(faqData: FaqData) {
				binding.apply {
					textView.text = faqData.title
					textView.clickDelay {
						XPopup.Builder(textView.context)
							.isDestroyOnDismiss(true)
							.hasShadowBg(true)
							.asCustom(FaqPopView(textView.context, faqData))
							.show()
					}
				}
			}
		}
	}

	inner class FaqPopView(context: Context, private val faqData: FaqData) :
		BottomPopupView(context) {

		override fun getImplLayoutId(): Int {
			return R.layout.pop_faq
		}

		override fun onCreate() {
			super.onCreate()
			val binding = PopFaqBinding.bind(this.popupImplView)
			binding.apply {
				titleTv.text = faqData.title
				questionTv.text = faqData.question
				answerTv.text = faqData.answer
				close.clickDelay {
					dismiss()
				}
			}
		}
	}

}
