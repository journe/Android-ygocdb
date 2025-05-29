package tech.jour.ygocdb.module.home.activity

import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import tech.jour.ygocdb.R
import tech.jour.ygocdb.common.ui.BaseActivity
import tech.jour.ygocdb.databinding.ActivityMainBinding

/**
 * 首页
 */
@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

	override val mViewModel by viewModels<MainViewModel>()

	private lateinit var navController: NavController
	private lateinit var appBarConfiguration: AppBarConfiguration

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
	}


	override fun initObserve() {

	}

	override fun initRequestData() {

	}

	override fun onSupportNavigateUp(): Boolean {
		return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
	}
}