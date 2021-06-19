package com.deas.movieapps.ui.list

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.deas.movieapps.BR
import com.deas.movieapps.R
import com.deas.movieapps.base.BaseFragment
import com.deas.movieapps.databinding.FragmentListMovieBinding
import com.deas.movieapps.ui.MovieViewModel
import javax.inject.Inject

/**
 * Created by igede@awantunai.com on 19/06/21.
 */

class ListMovieFragment : BaseFragment<FragmentListMovieBinding, MovieViewModel>() {

    @Inject
    internal lateinit var factoryModel: ViewModelProvider.Factory

    private lateinit var viewModel: MovieViewModel

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun getViewModel(): MovieViewModel {
        viewModel = ViewModelProviders.of(this, factoryModel).get(MovieViewModel::class.java)
        return viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.fetchListMovie(1)
    }
}