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
import tech.jour.ygocdb.module.home.database.SearchHistoryBean
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
class MainViewModel @Inject constructor(private val mRepository: MainRepository) : BaseViewModel() {

    val data = MutableLiveData<List<CardResult>>()

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
            viewModelScope.launch {
                insertSearchHistory(
                    SearchHistoryBean(
                        query = search,
                        updateTime = System.currentTimeMillis()
                    )
                )

                mRepository.search(search).cachedIn(viewModelScope).collectLatest {
                    _searchResult.value = it
                }

            }
        }
    }

    private suspend fun insertSearchHistory(bean: SearchHistoryBean) {
        mRepository.insertSearchHistory(bean)
    }
}