package com.deas.movieapps.ui.list

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.deas.movieapps.BR
import com.deas.movieapps.R
import com.deas.movieapps.base.BaseFragment
import com.deas.movieapps.ui.MovieViewModel
import javax.inject.Inject

/**
 * Created by igede@awantunai.com on 19/06/21.
 */

class ListMovieFragment : BaseFragment<FragmentListMovieBinding, MovieViewModel>() {

    @Inject
    internal lateinit var factoryModel: ViewModelProvider.Factory

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.list_movie_fragment

    override fun getViewModel(): MovieViewModel =
        ViewModelProviders.of(this, factoryModel).get(MovieViewModel::class.java)
}