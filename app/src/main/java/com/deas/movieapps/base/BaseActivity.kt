package com.deas.movieapps.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.deas.movieapps.di.component.AppComponent
import dagger.android.AndroidInjection

/**
 * Created by igede@awantunai.com on 19/06/21.
 */

abstract class BaseActivity<T : ViewDataBinding, V : ViewModel>() : AppCompatActivity() {

    private lateinit var mViewDataBinding: T
    private var mViewModel: V? = null

    fun getViewDataBinding(): T = mViewDataBinding

    abstract fun getBindingVariable(): Int
    abstract fun getLayoutId(): Int
    abstract fun getViewModel(): V

    override fun onCreate(savedInstanceState: Bundle?) {
        performDependencyInjection()
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    protected fun getAppComponent(): AppComponent = (application as MovieApplication).appComponent

    private fun performDataBinding() {
        mViewDataBinding = DataBindingUtil.setContentView(this, getLayoutId())
        this.mViewModel = if (mViewModel == null) getViewModel() else mViewModel
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding.executePendingBindings()

    }

    private fun performDependencyInjection() {
        AndroidInjection.inject(this)
    }

}