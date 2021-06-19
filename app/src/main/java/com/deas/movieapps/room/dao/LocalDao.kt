package com.deas.movieapps.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.deas.movieapps.room.entities.MovieListEntity

/**
 * Created by igede@awantunai.com on 19/06/21.
 */
@Dao
interface LocalDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertList(movieListEntity: MovieListEntity)
}