package com.deas.movieapps.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.deas.movieapps.network.ApiResponseCallback
import com.deas.movieapps.network.ApiService
import com.deas.movieapps.network.NetworkError
import com.deas.movieapps.network.Resource
import com.deas.movieapps.network.response.DetailMovieResponse
import com.deas.movieapps.network.response.MovieListResponse
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

/**
 * Created by igede@awantunai.com on 19/06/21.
 */

class MovieViewModel @Inject constructor(
    private val apiService: ApiService) : ViewModel(){

    private val compositeDisposable = CompositeDisposable()

    private val _movieList = MutableLiveData<Resource<MovieListResponse>>()
    val movieList : LiveData<Resource<MovieListResponse>> = _movieList

    private val _movieDetail = MutableLiveData<Resource<DetailMovieResponse>>()
    val movieDetail : LiveData<Resource<DetailMovieResponse>> = _movieDetail

    fun fetchListMovie(page : Int){
        _movieList.postValue(Resource.loading(null))
        compositeDisposable.add(apiService.fetchMovieList(page, object : ApiResponseCallback<MovieListResponse>{
            override fun onSuccess(response: MovieListResponse) {
                _movieList.postValue(Resource.success(response))
            }

            override fun onError(error: NetworkError) {
                _movieList.postValue(Resource.error(error.getErrorMessage(), null))
            }

        }))
    }

    fun fetchDetailMovie(movieId : Int){
        _movieDetail.postValue(Resource.loading(null))
        compositeDisposable.add(apiService.fetchMovieDetails(movieId, object : ApiResponseCallback<DetailMovieResponse>{
            override fun onSuccess(response: DetailMovieResponse) {
                _movieDetail.postValue(Resource.success(response))
            }

            override fun onError(error: NetworkError) {
                _movieDetail.postValue(Resource.error(error.getErrorMessage(), null))
            }

        }))
    }


}