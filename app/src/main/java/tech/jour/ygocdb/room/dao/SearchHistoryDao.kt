package tech.jour.ygocdb.room.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import tech.jour.ygocdb.module.home.database.SearchHistoryBean

@Dao
interface SearchHistoryDao {
    @Query("SELECT * FROM SearchHistoryBean ORDER BY  updateTime DESC  LIMIT :count")
    fun getRecentList(count: Int = 20): Flow<List<SearchHistoryBean>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(obj: SearchHistoryBean)

    @Query("DELETE FROM SearchHistoryBean")
    fun deleteAll()
}