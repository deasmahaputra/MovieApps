package com.deas.movieapps.room.entities

import androidx.room.Entity
import androidx.room.Index
import kotlinx.android.parcel.Parcelize

/**
 * Created by igede@awantunai.com on 19/06/21.
 */

@Parcelize
@Entity(
    tableName = "list_movie",
    indices = [
        Index(
            name = "unique_index_id",
            value = ["id", "id"],
            unique = true
        )
    ]
)
class MovieListEntity {
}