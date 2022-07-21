package tech.jour.ygocdb.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import tech.jour.ygocdb.module.home.database.SearchHistoryBean

@Dao
interface SearchHistoryDao {
//    @Query("SELECT * FROM SearchHistoryBean WHERE id = :id LIMIT 1")
//    fun getById(id: Int): SearchHistoryBean?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: SearchHistoryBean)
}