package tech.jour.ygocdb.module.home.activity

import android.content.SharedPreferences
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.preference.PreferenceManager
import dagger.hilt.android.AndroidEntryPoint
import tech.jour.ygocdb.R
import tech.jour.ygocdb.common.ui.BaseActivity
import tech.jour.ygocdb.databinding.ActivityMainBinding
import tech.jour.ygocdb.model.SettingBean
import tech.jour.ygocdb.model.SettingBean.CardListType
import tech.jour.ygocdb.module.settingLiveData

/**
 * 首页
 */
@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

	override val mViewModel by viewModels<MainViewModel>()

	private lateinit var navController: NavController
	private lateinit var appBarConfiguration: AppBarConfiguration

	private lateinit var sharedPreferences: SharedPreferences

	override fun ActivityMainBinding.initView() {
		setSupportActionBar(toolBar)
		val host: NavHostFragment =
			supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment?
				?: return
		navController = host.navController

		appBarConfiguration = AppBarConfiguration(
			setOf(
				R.id.homeFragment,
//				R.id.settingFragment,
//				R.id.aboutFragment
			),
			drawerLayout
		)
		setupActionBarWithNavController(navController, appBarConfiguration)
		navView.setupWithNavController(navController)

		initSharedPreferences()
	}

	private fun initSharedPreferences() {
		PreferenceManager.setDefaultValues(this, R.xml.preferences_main, false)
		sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
		mViewModel.sharedPreferences = sharedPreferences
		val listType = sharedPreferences.getString("setting_list_type", "0")?.toInt()
		when (listType) {
			0 -> settingLiveData.value?.cardListType = CardListType.SingleList()
			1 -> settingLiveData.value?.cardListType = CardListType.DoubleList()
		}

		val nameType = sharedPreferences.getString("setting_name_type", "0")?.toInt()
		when (nameType) {
			0 -> settingLiveData.value?.cardNameType = SettingBean.CardNameType.YGOPro()
			1 -> settingLiveData.value?.cardNameType = SettingBean.CardNameType.Cn()
			2 -> settingLiveData.value?.cardNameType = SettingBean.CardNameType.MD()
			3 -> settingLiveData.value?.cardNameType = SettingBean.CardNameType.NWBBS()
			4 -> settingLiveData.value?.cardNameType = SettingBean.CardNameType.CNOCG()
		}
	}


	override fun initObserve() {

	}

	override fun initRequestData() {
	}

	override fun onSupportNavigateUp(): Boolean {
		return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
	}
}