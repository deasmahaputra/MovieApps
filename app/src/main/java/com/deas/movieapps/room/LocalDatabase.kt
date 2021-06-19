package com.deas.movieapps.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.deas.movieapps.room.dao.LocalDao
import com.deas.movieapps.room.entities.MovieListEntity

/**
 * Created by igede@awantunai.com on 19/06/21.
 */

@Database(
    entities = [
        MovieListEntity::class,
    ],
    version = 1,
    exportSchema = false
)

abstract class LocalDatabase : RoomDatabase() {

    abstract fun localDao(): LocalDao

    companion object {
        private var INSTANCE: LocalDatabase? = null
        private const val databaseName = "localdatabase.db"

        fun getInstance(context: Context): LocalDatabase? {
            if (INSTANCE == null) {
                synchronized(LocalDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        LocalDatabase::class.java,
                        databaseName
                    ).allowMainThreadQueries().build()
                }
            }
            return INSTANCE as LocalDatabase
        }
    }
}