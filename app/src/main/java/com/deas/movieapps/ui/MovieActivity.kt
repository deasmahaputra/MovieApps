package com.deas.movieapps.ui

import android.os.Bundle
import com.deas.movieapps.R
import com.deas.movieapps.base.BaseActivity
import com.deas.movieapps.databinding.ActivityMovieBinding
import dagger.android.AndroidInjector
import dagger.android.HasAndroidInjector

/**
 * Created by igede@awantunai.com on 19/06/21.
 */

class MovieActivity : BaseActivity<ActivityMovieBinding, MovieViewModel>(), HasAndroidInjector {

    override fun getBindingVariable(): Int {
        TODO("Not yet implemented")
    }

    override fun getLayoutId(): Int {
        TODO("Not yet implemented")
    }

    override fun getViewModel(): MovieViewModel {
        TODO("Not yet implemented")
    }

    override fun androidInjector(): AndroidInjector<Any> {
        TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie)
    }
}