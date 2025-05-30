package tech.jour.ygocdb.model

data class SettingBean(
	var cardListType: CardListType = CardListType.SingleList(),
	var cardNameType: CardNameType = CardNameType.YGOPro()
) {
	sealed class CardListType(val type: Int) {
		class SingleList() : CardListType(0)
		class DoubleList() : CardListType(1)
	}

	sealed class CardNameType(val type: Int) {
		class YGOPro() : CardNameType(0)
		class Cn() : CardNameType(1)
		class MD() : CardNameType(2)
		class NWBBS() : CardNameType(3)
		class CNOCG() : CardNameType(4)
	}

}
