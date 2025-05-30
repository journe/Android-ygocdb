package tech.jour.ygocdb.module

import androidx.lifecycle.MutableLiveData
import tech.jour.ygocdb.model.CardResult
import tech.jour.ygocdb.model.SettingBean

/**
 * Created by journey on 2025/5/29.
 */
object Constant {
	var tempCardItem = CardResult()
}

val settingLiveData = MutableLiveData(SettingBean())
