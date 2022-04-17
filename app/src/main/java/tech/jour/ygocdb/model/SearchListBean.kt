package tech.jour.ygocdb.model

/**
 * Created by journey on 2022/4/15.
 */
data class SearchListBean(
	val next: Int,
	val result: List<CardResult>
)

data class CardResult(
	val artid: Int,
	val cid: Int,
	val cn_name: String,
	val cnocg_n: String,
	val `data`: CardData,
	val en_name: String,
	val faqs: List<String>,
	val id: Int,
	val jp_name: String,
	val jp_ruby: String,
	val text: CardText,
	val weight: Int
)

data class CardData(
	val atk: Int,
	val attribute: Int,
	val def: Int,
	val level: Int,
	val ot: Int,
	val race: Int,
	val setcode: Int,
	val type: Int
)

data class CardText(
	val desc: String,
	val pdesc: String,
	val types: String
)

fun CardResult.cardUrl() = "https://cdn.233.momobako.com/ygopro/pics/${this.id}.jpg!half"