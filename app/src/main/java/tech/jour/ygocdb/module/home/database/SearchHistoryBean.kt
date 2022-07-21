package tech.jour.ygocdb.module.home.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SearchHistoryBean(
    @PrimaryKey
    val query: String,
    val updateTime: Long,
)