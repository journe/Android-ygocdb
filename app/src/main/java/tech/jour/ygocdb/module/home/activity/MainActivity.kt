package tech.jour.ygocdb.module.home.activity

import android.view.KeyEvent
import android.widget.TextView
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
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

	override fun ActivityMainBinding.initView() {

		val host: NavHostFragment =
			supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment?
				?: return
		navController = host.navController

		setSupportActionBar(searchBar)

		searchBar.setupWithNavController(navController, drawerLayout)

		navView.setupWithNavController(navController)

//		drawerLayout.addDrawerListener(
//			ActionBarDrawerToggle(
//				this@MainActivity,
//				drawerLayout,
//				searchBar,
//				R.string.cat_navigationdrawer_button_show_content_description,
//				R.string.cat_navigationdrawer_button_hide_content_description
//			)
//		)

		setupSearchView()

	}

	private fun setupSearchView() {

		mBinding.searchView.editText
			.setOnEditorActionListener { v: TextView?, actionId: Int, event: KeyEvent? ->
				submitSearchQuery(
					mBinding.searchView.text.toString()
				)
				false
			}
//		mBinding.searchView.setOnBackClickListener(object : Search.OnBackClickListener {
//			override fun onBackClick(hasFocus: Boolean) {
//				Logger.d("onBackClick#hasFocus: $hasFocus")
//				if (hasFocus) {
//					mBinding.searchView.close()
//				} else {
//					mBinding.drawerLayout.open()
//				}
//			}
//		})
	}

	private fun submitSearchQuery(query: String) {
		mBinding.searchBar.setText(query)
		mBinding.searchView.hide()
//		mBinding.navView.setCheckedItem(R.id.searchFragment)
		mViewModel.submitSearch(query)
		navController.navigate(R.id.searchFragment)
	}

	override fun initObserve() {

	}

	override fun initRequestData() {
		lifecycleScope.launch {
			mViewModel.getRecentList().collectLatest {
				if (it != null) {
					mBinding.searchHistoryRv.adapter = SearchHistoryAdapter(it) { query ->
						submitSearchQuery(query)
					}
				}
			}
		}
	}


//    override fun onSupportNavigateUp(): Boolean {
//        return findNavController(R.id.nav_host_fragment_content_main).navigateUp(appBarConfiguration)
//    }

//	override fun onSupportNavigateUp(): Boolean {
//		val navController = findNavController(R.id.nav_host_fragment_content_main)
//		return navController.navigateUp(appBarConfiguration)
//				|| super.onSupportNavigateUp()
//	}
}