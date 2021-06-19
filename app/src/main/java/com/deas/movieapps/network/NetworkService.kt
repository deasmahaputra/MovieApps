package com.deas.movieapps.network

import com.deas.movieapps.BuildConfig
import com.deas.movieapps.network.response.DetailMovieResponse
import com.deas.movieapps.network.response.MovieListResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by igede@awantunai.com on 19/06/21.
 */
interface NetworkService {

    @GET(BuildConfig.ENDPOINT + "3/movie/{genre_id}/lists")
    fun fetchMovieList(@Query("api_key") apiKey : String,
                       @Query("page") page : Int) : Observable<MovieListResponse>


    @GET(BuildConfig.ENDPOINT + "3/movie/{movie_id}")
    fun fetchDetailMovie(@Path("movie_id") movieId : Int,
                         @Query("api_key") apiKey : String) : Observable<DetailMovieResponse>

}