package tech.jour.ygocdb.module.home.activity

import android.view.View
import androidx.activity.viewModels
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupWithNavController
import com.orhanobut.logger.Logger
import com.tcqq.searchview.Search
import dagger.hilt.android.AndroidEntryPoint
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.IFlexible
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import tech.jour.ygocdb.R
import tech.jour.ygocdb.common.ui.BaseActivity
import tech.jour.ygocdb.databinding.ActivityMainBinding
import tech.jour.ygocdb.model.SuggestionsItem


/**
 * 首页
 *
 * @author Qu Yunshuo
 * @since 5/22/21 2:26 PM
 */
@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    /**
     * 通过 viewModels() + Hilt 获取 ViewModel 实例
     */
    override val mViewModel by viewModels<MainViewModel>()

    private lateinit var suggestionsAdapter: FlexibleAdapter<IFlexible<*>>

    override fun ActivityMainBinding.initView() {

        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment_content_main) as NavHostFragment? ?: return
        val navController = host.navController

        mBinding.navView.setupWithNavController(navController)

        suggestionsAdapter = FlexibleAdapter(null, object : FlexibleAdapter.OnItemClickListener {
            override fun onItemClick(view: View?, position: Int): Boolean {
                val item = suggestionsAdapter.getItem(position, SuggestionsItem::class.java)!!
                mBinding.layoutHome.searchView.setQuery(item.query, true)
                return false
            }

        }, true)

        mBinding.layoutHome.searchView.adapter = suggestionsAdapter
        mBinding.layoutHome.searchView.setOnQueryTextListener(object : Search.OnQueryTextListener {
            override fun onQueryTextSubmit(query: CharSequence) {
//                Logger.d("onQueryTextSubmit#query: $query")
                mBinding.layoutHome.searchView.close(false)
                mViewModel.submitSearch(query.toString())
                navController.navigate(R.id.searchFragment)
            }

            override fun onQueryTextChange(newText: CharSequence) {
//                Logger.d("onQueryTextChange#newText: $newText")
            }
        })
        mBinding.layoutHome.searchView.setOnBackClickListener(object : Search.OnBackClickListener {
            override fun onBackClick(hasFocus: Boolean) {
                Logger.d("onBackClick#hasFocus: $hasFocus")
                if (hasFocus) {
                    mBinding.layoutHome.searchView.close()
                } else {
                    mBinding.drawerLayout.open()
                }
            }
        })
        mBinding.navView.setNavigationItemSelectedListener {
            navController.navigate(it.itemId)
            mBinding.drawerLayout.close()
            true
        }
        mBinding.drawerLayout.addDrawerListener(object : DrawerLayout.DrawerListener {
            override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
//                if (slideOffset < 0.3) animateLogoHamburgerToLogoArrow()
            }

            override fun onDrawerOpened(drawerView: View) {
                mBinding.layoutHome.searchView.setLogoHamburgerToLogoArrowWithAnimation(true)
            }

            override fun onDrawerClosed(drawerView: View) {
                mBinding.layoutHome.searchView.setLogoHamburgerToLogoArrowWithAnimation(false)
            }

            override fun onDrawerStateChanged(newState: Int) {
            }

        })
    }

    override fun initObserve() {

    }

    override fun initRequestData() {
        lifecycleScope.launch {
            mViewModel.getRecentList().collectLatest {
                if (it != null) {
                    suggestionsAdapter.updateDataSet(
                        it.map {
                            SuggestionsItem(query = it.query)
                        }
                    )
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