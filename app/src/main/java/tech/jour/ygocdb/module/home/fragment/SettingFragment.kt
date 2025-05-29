package tech.jour.ygocdb.module.home.fragment

import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.Preference.SummaryProvider
import androidx.preference.PreferenceFragmentCompat
import tech.jour.ygocdb.R
import tech.jour.ygocdb.base.utils.getVersionName
import tech.jour.ygocdb.model.SettingBean
import tech.jour.ygocdb.model.SettingBean.CardListType
import tech.jour.ygocdb.module.settingLiveData
import java.util.Objects


class SettingFragment : PreferenceFragmentCompat() {
	override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
		// Load the preferences from an XML resource
		addPreferencesFromResource(R.xml.preferences_main)

		// 设置版本号
		val verName = getVersionName()
		val pfVersion = findPreference<Preference?>("setting_version")
		pfVersion?.setSummary(verName)

		val cardListType = findPreference<ListPreference?>("setting_list_type")
		if (cardListType != null) {
			// 使用自定义 SummaryProvider
			cardListType.setSummaryProvider(
				SummaryProvider { preference: ListPreference? ->
					Objects.requireNonNull<CharSequence?>(
						preference!!.getEntry()
					)
				})
			cardListType.onPreferenceChangeListener =
				Preference.OnPreferenceChangeListener { preference: Preference?, newValue: Any? ->
					val type = newValue.toString().toInt()
					when (type) {
						0 -> settingLiveData.value?.cardListType = CardListType.SingleList()
						1 -> settingLiveData.value?.cardListType = CardListType.DoubleList()
					}

					newValue.toString().trim { it <= ' ' }.isNotEmpty()

				}
		}
		val nameType = findPreference<ListPreference?>("setting_name_type")
		if (nameType != null) {
			// 使用自定义 SummaryProvider
			nameType.setSummaryProvider(
				SummaryProvider { preference: ListPreference? ->
					Objects.requireNonNull<CharSequence?>(
						preference!!.getEntry()
					)
				})
			nameType.onPreferenceChangeListener =
				Preference.OnPreferenceChangeListener { preference: Preference?, newValue: Any? ->
					val type = newValue.toString().toInt()
					when (type) {
						0 -> settingLiveData.value?.cardNameType = SettingBean.CardNameType.YGOPro()
						1 -> settingLiveData.value?.cardNameType = SettingBean.CardNameType.Cn()
						2 -> settingLiveData.value?.cardNameType = SettingBean.CardNameType.MD()
						3 -> settingLiveData.value?.cardNameType = SettingBean.CardNameType.NWBBS()
						4 -> settingLiveData.value?.cardNameType = SettingBean.CardNameType.CNOCG()
					}

					newValue.toString().trim { it <= ' ' }.isNotEmpty()

				}
		}
	}
}
//	override val mViewModel: EmptyViewModel by viewModels()
//	override fun FragmentSettingBinding.initView() {
//		mBinding.emailAdress.clickDelay {
//			ClipboardUtils.copyText("androidcoder.lv@qq.com")
//			val data = Intent(Intent.ACTION_SENDTO)
//			data.data = Uri.parse("mailto:androidcoder.lv@qq.com")
//			data.putExtra(Intent.EXTRA_SUBJECT, "ygocdb-android Feedback")
//			data.putExtra(Intent.EXTRA_TEXT, "")
//			startActivity(data)
//		}
//	}
//
//	override fun initObserve() {
//	}
//
//	override fun initRequestData() {
//	}
//
//}