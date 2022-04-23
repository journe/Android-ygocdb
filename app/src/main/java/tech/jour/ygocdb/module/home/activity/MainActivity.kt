package tech.jour.ygocdb.module.home.activity

import android.app.SearchManager
import android.content.Context
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import dagger.hilt.android.AndroidEntryPoint
import tech.jour.ygocdb.R
import tech.jour.ygocdb.base.utils.toast
import tech.jour.ygocdb.common.ui.BaseActivity
import tech.jour.ygocdb.databinding.ActivityMainBinding
import tech.jour.ygocdb.model.CardResult

/**
 * 首页
 *
 * @author Qu Yunshuo
 * @since 5/22/21 2:26 PM
 */
@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, HomeViewModel>() {

	/**
	 * 通过 viewModels() + Hilt 获取 ViewModel 实例
	 */
	override val mViewModel by viewModels<HomeViewModel>()

	private lateinit var appBarConfiguration: AppBarConfiguration

	private lateinit var searchView: SearchView

	private lateinit var searchMenu: MenuItem

	// Get the SearchView and set the searchable configuration
	private lateinit var searchManager: SearchManager

	override fun ActivityMainBinding.initView() {
		val binding = mBinding
		setContentView(binding.root)

		setSupportActionBar(binding.toolbar)

		searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager


		val host: NavHostFragment = supportFragmentManager
			.findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment? ?: return
		val navController = host.navController

		appBarConfiguration = AppBarConfiguration(
			setOf(
				R.id.FirstFragment,
				R.id.SecondFragment,
			),//顶层导航设置
			mBinding.drawerLayout
		)
		setupActionBarWithNavController(navController, appBarConfiguration)
		mBinding.navView.setupWithNavController(navController)

		binding.fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
			showSearch(view as FloatingActionButton)
//			mViewModel.getData()
		}
	}

	override fun initObserve() {
//		observeLiveData(mViewModel.data, ::processData)
	}

	private fun processData(data: List<CardResult>) {
//        mBinding.vTvHello.text = data
//        mBinding.vTvHello.setTextColor(Color.BLUE)
	}


	private fun showSearch(fab: FloatingActionButton) {
		if (searchMenu.isActionViewExpanded) {
			mViewModel.submitSearch(searchView.query.toString())
			searchMenu.collapseActionView()
			fab.setImageResource(R.drawable.ic_baseline_search_24)
		} else {
			searchMenu.expandActionView()
			fab.setImageResource(R.drawable.ic_baseline_send_24)
		}
	}

	override fun initRequestData() {

	}

	override fun onCreateOptionsMenu(menu: Menu): Boolean {
		// Inflate the menu; this adds items to the action bar if it is present.
		menuInflater.inflate(R.menu.menu_main, menu)

		searchMenu = menu.findItem(R.id.menu_search)
		searchView = searchMenu.actionView as SearchView

		searchView.apply {

			// Assumes current activity is the searchable activity
			setSearchableInfo(searchManager.getSearchableInfo(componentName))
			setIconifiedByDefault(false) // Do not iconify the widget; expand it by default
			//搜索框展开时后面叉叉按钮的点击事件
			setOnCloseListener {
				false
			}
			//搜索图标按钮(打开搜索框的按钮)的点击事件
			setOnSearchClickListener {
				setQuery("黑羽", false)
			}
			//搜索框文字变化监听
			setOnQueryTextListener(object : SearchView.OnQueryTextListener {
				override fun onQueryTextSubmit(s: String): Boolean {
//					toast("搜索了:$s")
//					mViewModel.getData(s)
					searchMenu.collapseActionView()
					mBinding.fab.setImageResource(R.drawable.ic_baseline_search_24)
					mViewModel.submitSearch(s)
					return false
				}

				override fun onQueryTextChange(s: String): Boolean {
					return false
				}
			})

		}

		return true
	}

	override fun onOptionsItemSelected(item: MenuItem): Boolean {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		return when (item.itemId) {
			R.id.action_settings -> true
			R.id.menu_search -> true
			else -> super.onOptionsItemSelected(item)
		}
	}

	override fun onSupportNavigateUp(): Boolean {
		return findNavController(R.id.nav_host_fragment_content_main).navigateUp(appBarConfiguration)
	}

//	override fun onSupportNavigateUp(): Boolean {
//		val navController = findNavController(R.id.nav_host_fragment_content_main)
//		return navController.navigateUp(appBarConfiguration)
//				|| super.onSupportNavigateUp()
//	}
}