package tech.jour.ygocdb.module.home

import android.content.Context
import android.widget.TextView
import com.lxj.xpopup.core.AttachPopupView
import tech.jour.ygocdb.R
import tech.jour.ygocdb.model.CardText

/**
 * Description: 自定义背景的Attach弹窗
 * Create by lxj, at 2019/3/13
 */
class CardDetailAttachPopup(context: Context, private val cardText: CardText) : AttachPopupView(context) {
	override fun getImplLayoutId(): Int {
		return R.layout.custom_attach_popup
	}

	override fun onCreate() {
		super.onCreate()
		val tv = findViewById<TextView>(R.id.types)
		tv.text = cardText.types
		val desc = findViewById<TextView>(R.id.desc)
		desc.text = cardText.desc
	}
}