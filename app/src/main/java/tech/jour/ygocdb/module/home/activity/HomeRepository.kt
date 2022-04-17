package tech.jour.ygocdb.module.home.activity

import androidx.paging.Pager
import androidx.paging.PagingConfig
import tech.jour.ygocdb.base.mvvm.m.BaseRepository
import tech.jour.ygocdb.model.CardResult
import tech.jour.ygocdb.module.home.fragment.SearchPagingSource
import tech.jour.ygocdb.module.home.net.HomeApiService
import javax.inject.Inject

/**
 * 首页M层
 *
 * @author Qu Yunshuo
 * @since 5/25/21 5:42 PM
 */
class HomeRepository @Inject constructor() : BaseRepository() {

	@Inject
	lateinit var mApi: HomeApiService

	/**
	 * 模拟获取数据
	 */
	suspend fun getData(search: String) = request<List<CardResult>> {
		val res = mApi.getSearch(search)
		emit(res.result)
	}

	fun search(query: String) =
		Pager(PagingConfig(pageSize = 20)) { SearchPagingSource(mApi, query) }.flow


}