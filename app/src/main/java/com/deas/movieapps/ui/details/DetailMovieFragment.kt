package com.deas.movieapps.ui.details

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.deas.movieapps.BR
import com.deas.movieapps.R
import com.deas.movieapps.base.BaseFragment
import com.deas.movieapps.databinding.FragmentDetailMovieBinding
import com.deas.movieapps.databinding.FragmentListMovieBinding
import com.deas.movieapps.ui.MovieViewModel
import javax.inject.Inject

/**
 * Created by igede@awantunai.com on 19/06/21.
 */

class DetailMovieFragment : BaseFragment<FragmentDetailMovieBinding, MovieViewModel>() {

    @Inject
    internal lateinit var factoryModel: ViewModelProvider.Factory

    private lateinit var viewModel: MovieViewModel

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.fragment_detail_movie

    override fun getViewModel(): MovieViewModel {
        viewModel = ViewModelProviders.of(this, factoryModel).get(MovieViewModel::class.java)
        return viewModel
    }


}