package tech.jour.ygocdb.module.home.activity

import androidx.paging.Pager
import androidx.paging.PagingConfig
import org.jsoup.Jsoup
import tech.jour.ygocdb.base.mvvm.m.BaseRepository
import tech.jour.ygocdb.model.CardDetailJsoupBean
import tech.jour.ygocdb.model.CardResult
import tech.jour.ygocdb.module.home.database.SearchHistoryBean
import tech.jour.ygocdb.module.home.fragment.SearchPagingSource
import tech.jour.ygocdb.module.home.net.HomeApiService
import tech.jour.ygocdb.module.home.net.JsoupParser
import tech.jour.ygocdb.room.dao.SearchHistoryDao
import javax.inject.Inject

class MainRepository @Inject constructor() : BaseRepository() {

	@Inject
	lateinit var mApi: HomeApiService

	@Inject
	lateinit var historyDao: SearchHistoryDao

	/**
	 * 模拟获取数据
	 */
	suspend fun getData(search: String) = request<List<CardResult>> {
		val res = mApi.getSearch(search)
		emit(res.result)
	}

	fun search(query: String) =
		Pager(PagingConfig(pageSize = 20)) { SearchPagingSource(mApi, query) }.flow

	fun insertSearchHistory(bean: SearchHistoryBean) {
		historyDao.insert(bean)
	}

	fun getRecentList() = historyDao.getRecentList()

	fun getCardDetail(cardId: Long) = request<CardDetailJsoupBean> {
		val result = mApi.getCardDetail(cardId)
		val bean = JsoupParser.parseCardDetail(
			Jsoup.parse(result.string())
		)
		emit(bean)
	}
}