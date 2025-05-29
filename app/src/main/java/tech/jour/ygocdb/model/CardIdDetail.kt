package tech.jour.ygocdb.model

/**
 * Created by journey on 2025/5/28.
 * {
 * "id": 36687247,
 * "data": {
 * "ot": 3,
 * "setcode": 39,
 * "type": 33,
 * "atk": 1600,
 * "def": 800,
 * "level": 4,
 * "race": 16384,
 * "attribute": 1
 * },
 * "text": {
 * "name": "科技属 突冲犀牛",
 * "types": "[怪兽|效果] 兽/地\n[★4] 1600/800",
 * "desc": "这张卡攻击的场合，伤害步骤内这张卡的攻击力上升400。场上存在的这张卡被破坏送去墓地的回合的结束阶段时，可以从自己卡组把「科技属 突冲犀牛」以外的1只名字带有「科技属」的怪兽加入手卡。"
 * }
 * }
 */
data class CardIdDetail(
    val `data`: Data? = null,
    val id: Int? = null,
    val text: Text? = null
)

data class Data(
    val atk: Int? = null,
    val attribute: Int? = null,
    val def: Int? = null,
    val level: Int? = null,
    val ot: Int? = null,
    val race: Int? = null,
    val setcode: Int? = null,
    val type: Int? = null
)

data class Text(
    val desc: String? = null,
    val name: String? = null,
    val types: String? = null
)