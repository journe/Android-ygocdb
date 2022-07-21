package tech.jour.ygocdb.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import tech.jour.ygocdb.room.dao.SearchHistoryDao
import tech.jour.ygocdb.base.BaseApplication
import tech.jour.ygocdb.module.home.database.SearchHistoryBean

/**
 * Created by journey on 2020/5/18.
 */
@Database(
    entities = [SearchHistoryBean::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun searchHistoryDao(): SearchHistoryDao

    companion object {
        private const val DATABASE_NAME: String = "ygo.db"

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null
        fun getInstance(context: Context = BaseApplication.context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
//          .addCallback(object : RoomDatabase.Callback() {
//            override fun onCreate(db: SupportSQLiteDatabase) {
//              super.onCreate(db)
//              val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
//              WorkManager.getInstance(context)
//                  .enqueue(request)
//            }
//          })
                .build()
        }
    }
}
