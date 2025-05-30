package tech.jour.ygocdb.model

/**
 *         {
 *             "cid": 17414,
 *             "id": 74078255,
 *             "cn_name": "珠泪哀歌族·梅洛人鱼",
 *             "sc_name": "泪冠哀歌・梅露",
 *             "md_name": "泪冠哀歌・梅露",
 *             "nwbbs_n": "珠泪哀歌族·梅洛人鱼",
 *             "cnocg_n": "哀泪姬 小人鱼",
 *             "jp_ruby": "ティアラメンツ・メイルゥ",
 *             "jp_name": "ティアラメンツ・メイルゥ",
 *             "en_name": "Tearlaments Merrli",
 *             "text": {
 *                 "types": "[怪兽|效果] 水/暗\n[★2] 800/2000",
 *                 "pdesc": "",
 *                 "desc": "这个卡名的①②的效果1回合各能使用1次。\r\n①：这张卡召唤·特殊召唤成功的场合才能发动。从自己卡组上面把3张卡送去墓地。\r\n②：这张卡被效果送去墓地的场合才能发动。融合怪兽卡决定的包含墓地的这张卡的融合素材怪兽从自己的手卡·场上·墓地用喜欢的顺序回到持有者卡组下面，把那1只融合怪兽从额外卡组融合召唤。"
 *             },
 *             "data": {
 *                 "ot": 11,
 *                 "setcode": 385,
 *                 "type": 33,
 *                 "atk": 800,
 *                 "def": 2000,
 *                 "level": 2,
 *                 "race": 64,
 *                 "attribute": 32
 *             },
 *             "html": {
 *                 "pdesc": "",
 *                 "desc": "这个卡名的①②的效果1回合各能使用1次。<br>①：这张卡召唤·特殊召唤成功的场合才能发动。从自己卡组上面把3张卡送去墓地。<br>②：这张卡被效果送去墓地的场合才能发动。融合怪兽卡决定的包含墓地的这张卡的融合素材怪兽从自己的手卡·场上·墓地用喜欢的顺序回到持有者卡组下面，把那1只融合怪兽从额外卡组融合召唤。",
 *                 "refer": {}
 *             },
 *             "weight": 90,
 *             "faqs": [
 *                 "23502"
 *             ],
 *             "artid": 0
 *         }
 */
data class CardDetailJsoupBean(
	val cid: Int? = null,
	val avail: List<AvailPack> = emptyList(),
	val packs: List<PackData> = emptyList(),
	val supplements: List<String> = emptyList(),
	val faqs: List<FaqData> = emptyList(),
) {
	data class PackData(
		var id: Long? = null,
		var date: String? = null,
		var code: String? = null,
		var name: String? = null,
	)

	data class AvailPack(
		var id: Long? = null,
		var tag: String? = null,
		var name: String? = null,
	)

	data class FaqData(
		var title: String = "",
		var question: String = "",
		var answer: String = "",
	)

}
