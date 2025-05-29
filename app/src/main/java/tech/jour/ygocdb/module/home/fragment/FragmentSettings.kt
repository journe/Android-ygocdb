package tech.jour.ygocdb.module.home.fragment

import android.os.Bundle
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.Preference.SummaryProvider
import androidx.preference.PreferenceFragmentCompat
import tech.jour.ygocdb.R
import tech.jour.ygocdb.base.utils.getVersionName
import java.util.Objects

class FragmentSettings : PreferenceFragmentCompat() {
	override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
		// Load the preferences from an XML resource
		addPreferencesFromResource(R.xml.preferences_main)

		// 设置版本号
		val verName = getVersionName()
		val pfVersion = findPreference<Preference?>("setting_version")
		pfVersion?.setSummary(verName)

		val pfJoystick = findPreference<ListPreference?>("setting_list_type")
		if (pfJoystick != null) {
			// 使用自定义 SummaryProvider
			pfJoystick.setSummaryProvider(
				SummaryProvider { preference: ListPreference? ->
					Objects.requireNonNull<CharSequence?>(
						preference!!.getEntry()
					)
				})
			pfJoystick.onPreferenceChangeListener =
				Preference.OnPreferenceChangeListener { preference: Preference?, newValue: Any? ->
					newValue.toString().trim { it <= ' ' }.isNotEmpty()
				}
		}
	}
}