package com.deas.movieapps.room.entities

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
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
data class MovieListEntity(
    @NonNull @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") var id: Long = 0,
    @ColumnInfo(name = "name") var name: String?,
    @ColumnInfo(name = "poster_path") var poster_path: String?,
    @ColumnInfo(name = "description") var description: String?,
) : Parcelable{

}
