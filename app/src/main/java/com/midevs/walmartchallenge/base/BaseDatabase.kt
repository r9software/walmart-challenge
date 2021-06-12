package com.midevs.walmartchallenge.base

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.midevs.walmartchallenge.models.Movie
import com.midevs.walmartchallenge.models.MovieDao
import com.midevs.walmartchallenge.utils.Converters
import java.util.concurrent.Executors


@Database(
    entities = arrayOf(Movie::class),
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class BaseDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

    companion object {
        var INSTANCE: BaseDatabase? = null

        fun getAppDataBase(context: Context): BaseDatabase? {
            if (INSTANCE == null) {
                synchronized(BaseDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        BaseDatabase::class.java,
                        "database.db"
                    )
                        .addCallback(object : Callback() {
                            override fun onCreate(db: SupportSQLiteDatabase) {
                                Executors.newSingleThreadExecutor().execute {

                                }
                            }

                            override fun onOpen(db: SupportSQLiteDatabase) {
                                // do something every time database is open
                            }
                        })
                        .build()
                }
            }
            return INSTANCE
        }
    }
}