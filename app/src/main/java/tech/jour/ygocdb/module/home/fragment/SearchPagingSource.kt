package tech.jour.ygocdb.module.home.fragment

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.orhanobut.logger.Logger
import retrofit2.HttpException
import tech.jour.ygocdb.model.CardResult
import tech.jour.ygocdb.module.home.net.HomeApiService
import java.io.IOException

class SearchPagingSource(
	private val backend: HomeApiService,
	private val query: String
) : PagingSource<Int, CardResult>() {
	override suspend fun load(
		params: LoadParams<Int>
	): LoadResult<Int, CardResult> {
		return try {
			// Start refresh at page 1 if undefined.
			val nextPageNumber = params.key ?: 1
			val response = backend.getSearch(query)
			LoadResult.Page(
				data = response.result,
				prevKey = null, // Only paging forward.
				nextKey = null
			)
		} catch (e: IOException) {
			Logger.d(e.message)
			LoadResult.Error(e)
		} catch (e: HttpException) {
			Logger.d(e.message)
			LoadResult.Error(e)
		}
	}

	override fun getRefreshKey(state: PagingState<Int, CardResult>): Int? {
		// Try to find the page key of the closest page to anchorPosition, from
		// either the prevKey or the nextKey, but you need to handle nullability
		// here:
		//  * prevKey == null -> anchorPage is the first page.
		//  * nextKey == null -> anchorPage is the last page.
		//  * both prevKey and nextKey null -> anchorPage is the initial page, so
		//    just return null.
		return state.anchorPosition?.let { anchorPosition ->
			val anchorPage = state.closestPageToPosition(anchorPosition)
			anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
		}
	}
}