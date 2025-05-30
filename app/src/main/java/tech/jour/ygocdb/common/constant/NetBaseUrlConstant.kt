package tech.jour.ygocdb.common.constant

/**
 * 接口公共地址
 *
 * @author Qu Yunshuo
 * @since 4/17/21 3:27 PM
 */
internal object NetBaseUrlConstant {

	val MAIN_URL = "https://ygocdb.com"
		get() {
			if (field.isEmpty()) {
				throw NotImplementedError("MAIN_URL ERROR")
			}
			return field
		}

	const val SEARCH = "/api/v0/"
	const val CARDID = "/api/v0/card/"
	const val CARD_DETAIL = "/card/{id}"
	const val PACK_LIST = "/pack/{id}"
}