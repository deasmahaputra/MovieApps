package com.deas.movieapps

import org.junit.ClassRule
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.deas.movieapps.network.ApiService
import com.deas.movieapps.network.NetworkService
import com.deas.movieapps.network.response.MovieListResponse
import com.deas.movieapps.ui.MovieViewModel
import com.nhaarman.mockitokotlin2.spy
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito

/**
 * Created by igede@awantunai.com on 19/06/21.
 */

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    // A JUnit Test Rule that swaps the background executor used by
    // the Architecture Components with a different one which executes each task synchronously.
    // You can use this rule for your host side tests that use Architecture Components.
    @Rule
    @JvmField
    var rule = InstantTaskExecutorRule()

    // Test rule for making the RxJava to run synchronously in unit test
    companion object {
        @ClassRule
        @JvmField
        val schedulers = RxImmedietSchedulerRule()
    }

    @Mock
    lateinit var networkService: NetworkService

    private lateinit var apiService: ApiService

    private lateinit var savedStateHandle: SavedStateHandle

    lateinit var movieViewModel: MovieViewModel

    @Before
    fun setup() {
        apiService = spy(ApiService(networkService))
        savedStateHandle = spy(SavedStateHandle())
        movieViewModel = MovieViewModel(apiService)
    }

    @Test
    fun `fetch movie list`(){
        //Mockito.`when`(movieViewModel.fetchListMovie(1)).thenReturn(Observable.just(MovieListResponse))
    }

}