package com.deas.movieapps.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.deas.movieapps.BR
import com.deas.movieapps.R
import com.deas.movieapps.base.BaseActivity
import com.deas.movieapps.databinding.ActivityMovieBinding
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * Created by igede@awantunai.com on 19/06/21.
 */

class MovieActivity : BaseActivity<ActivityMovieBinding, MovieViewModel>(),
    HasSupportFragmentInjector {

    @Inject
    internal lateinit var factoryModel: ViewModelProvider.Factory

    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private lateinit var navController: NavController

    override fun getBindingVariable(): Int = BR.viewModel

    override fun getLayoutId(): Int = R.layout.activity_movie

    override fun getViewModel(): MovieViewModel =
        ViewModelProviders.of(this, factoryModel).get(MovieViewModel::class.java)

    override fun supportFragmentInjector(): AndroidInjector<Fragment> = fragmentDispatchingAndroidInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViews()
    }

    private fun initViews() {
        navController = findNavController(R.id.nav_host_fragment)
    }
    
}