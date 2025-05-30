package tech.jour.ygocdb.module.home.net

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import tech.jour.ygocdb.common.constant.NetBaseUrlConstant
import tech.jour.ygocdb.model.CardDetailJsoupBean
import tech.jour.ygocdb.model.CardIdDetail
import tech.jour.ygocdb.model.SearchListBean

/**
 * Home模块的接口
 */
interface HomeApiService {
	@GET(NetBaseUrlConstant.SEARCH)
	suspend fun getSearch(@Query("search") search: String): SearchListBean

	@GET("/api/v0/card/{id}")
	suspend fun getByCardId(@Path("id") cardId: Long): CardIdDetail

	@GET(NetBaseUrlConstant.CARD_DETAIL)
	suspend fun getCardDetail(@Path("id") cardId: Long): ResponseBody

	@GET(NetBaseUrlConstant.PACK_LIST)
	suspend fun getPackList(@Path("id") packId: Long): ResponseBody

}
