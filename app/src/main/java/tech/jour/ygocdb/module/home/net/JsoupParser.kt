package tech.jour.ygocdb.module.home.net

import org.jsoup.nodes.Document
import tech.jour.ygocdb.base.ktx.d
import tech.jour.ygocdb.model.CardDetailJsoupBean
import tech.jour.ygocdb.model.CardDetailJsoupBean.AvailPack
import tech.jour.ygocdb.model.CardDetailJsoupBean.FaqData
import tech.jour.ygocdb.model.CardDetailJsoupBean.PackData

object JsoupParser {
	fun parseCardDetail(doc: Document): CardDetailJsoupBean {
		val availList = mutableListOf<AvailPack>()
		val packList = mutableListOf<PackData>()
		val supplements = mutableListOf<String>()
		val faqDatas = mutableListOf<FaqData>()

		val detailBody = doc.body()
			.select("main")
			.select(".container")

		val tags = detailBody.select(".avail").select("i")
		val apacks = detailBody.select(".avail").select("span")

		tags.zip(apacks).forEach {
			val tag = it.first.text()
			val packName = it.second.text()
			val packId = it.second.select("a")
				.attr("href")
				.substringAfterLast("/")
			availList.add(AvailPack(tag = tag, id = packId.toLong(), name = packName))
		}

		val packs = detailBody.select(".packs").select("li")
		packs.forEach {
			val date = it.select("span").first().text()
			val code = it.select("span").last().text()
			val packName = it.select("a").text()
			val packId = it.select("a")
				.attr("href")
				.substringAfterLast("/")
			packList.add(PackData(date = date, code = code, name = packName, id = packId.toLong()))
		}

		val cid = detailBody.select(".cid").text().toInt()

		val additions = detailBody.select(".qa.supplement").select("li")
		additions.forEach {
			supplements.add(it.text())
		}

		val faqs = detailBody.select(".qabox")
		faqs.forEach {
			val title = it.select(".qa.title").text()
			val question = it.select(".qa.question").text()
			val answer = it.select(".qa.answer").text()
			if (title.isNotEmpty()) {
				faqDatas.add(FaqData(title, question, answer))
			}
		}
		return CardDetailJsoupBean(
			cid = cid,
			avail = availList,
			packs = packList,
			supplements,
			faqDatas
		)
	}
}