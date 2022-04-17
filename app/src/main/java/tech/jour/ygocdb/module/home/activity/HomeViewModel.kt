package tech.jour.ygocdb.module.home.activity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.orhanobut.logger.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import tech.jour.ygocdb.base.ktx.launchIO
import tech.jour.ygocdb.base.mvvm.vm.BaseViewModel
import tech.jour.ygocdb.model.CardResult
import javax.inject.Inject

/**
 * 首页的VM层
 *
 * @property mRepository HomeRepository 仓库层 通过Hilt注入
 *
 * @author Qu Yunshuo
 * @since 5/25/21 5:41 PM
 */
@HiltViewModel
class HomeViewModel @Inject constructor(private val mRepository: HomeRepository) : BaseViewModel() {

	val data = MutableLiveData<List<CardResult>>()

	private val _searchWord = MutableStateFlow("")

	val searchWord: StateFlow<String> = _searchWord

//	val searchResult = _searchWord.flatMapLatest {
//		mRepository.search(it).catch {
//			Logger.d("mRepository searchResult error")
//		}.cachedIn(viewModelScope).stateIn(viewModelScope)
//	}.catch {
//		Logger.d("searchResult error")
//	}


	private val _searchResult = MutableLiveData<PagingData<CardResult>>()
	val searchResult: LiveData<PagingData<CardResult>> = _searchResult

	/**
	 * 模拟获取数据
	 */
	fun getData(search: String) {
		launchIO {
			mRepository.getData(search)
				.catch { Logger.d("getData: $it") }
				.collect { data.postValue(it) }
		}
	}

	fun search(search: String) = mRepository.search(search)
		.cachedIn(viewModelScope)

	fun submitSearch(search: String) {
		if (!search.isNullOrEmpty()) {
//			_searchWord.value = search
			viewModelScope.launch {
				mRepository.search(search).cachedIn(viewModelScope).collectLatest {
					_searchResult.value = it
				}
			}
		}

	}
}