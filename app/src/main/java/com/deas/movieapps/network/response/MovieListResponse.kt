package com.deas.movieapps.network.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by igede@awantunai.com on 19/06/21.
 */


data class MovieListResponse(var results : MutableList<MovieList>) {

    data class MovieList(var id: Int? = null,
                         var name: String? = null,
                         var poster_path: String? = null,
                         var description : String? = null)
}