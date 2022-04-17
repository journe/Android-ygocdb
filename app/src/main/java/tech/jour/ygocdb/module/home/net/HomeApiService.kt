package tech.jour.ygocdb.module.home.net

import retrofit2.http.GET
import retrofit2.http.Query
import tech.jour.ygocdb.common.constant.NetBaseUrlConstant
import tech.jour.ygocdb.model.SearchListBean

/**
 * Home模块的接口
 *
 * @author Qu Yunshuo
 * @since 6/4/21 5:51 PM
 */
interface HomeApiService {
	@GET(NetBaseUrlConstant.SEARCH)
	suspend fun getSearch(@Query("search") search: String): SearchListBean

}
