package com.deas.movieapps.network

import com.deas.movieapps.BuildConfig
import com.deas.movieapps.extension.configured
import com.deas.movieapps.extension.subscribe
import com.deas.movieapps.network.response.DetailMovieResponse
import com.deas.movieapps.network.response.MovieListResponse
import io.reactivex.disposables.Disposable

/**
 * Created by igede@awantunai.com on 19/06/21.
 */

class ApiService(private val networkService: NetworkService){

    fun fetchMovieList(page : Int, callback: ApiResponseCallback<MovieListResponse>) : Disposable {
        return networkService.fetchMovieList(BuildConfig.APIKEY, page)
            .configured()
            .subscribe({callback.onSuccess(it)}, { error : NetworkError -> callback.onError(error)})
    }

    fun fetchMovieDetails(movieId : Int, callback: ApiResponseCallback<DetailMovieResponse>) : Disposable{
        return networkService.fetchDetailMovie(movieId, BuildConfig.APIKEY)
            .configured()
            .subscribe({callback.onSuccess(it)}, { error : NetworkError -> callback.onError(error)})
    }
}