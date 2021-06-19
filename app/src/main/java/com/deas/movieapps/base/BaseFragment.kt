package com.deas.movieapps.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import com.deas.movieapps.di.component.AppComponent
//import com.deas.movieapps.di.component.AppComponent
import dagger.android.support.AndroidSupportInjection

/**
 * Created by igede@awantunai.com on 19/06/21.
 */

abstract class BaseFragment<T : ViewDataBinding, V : ViewModel> : Fragment() {

    private lateinit var mViewDataBinding: T
    private var mViewModel: V? = null

    fun getViewDataBinding(): T = mViewDataBinding

    abstract fun getBindingVariable(): Int
    abstract fun getLayoutId(): Int
    abstract fun getViewModel(): V

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        performDataBinding()
    }

    protected fun getAppComponent(): AppComponent = (activity?.application as MovieApplication).appComponent

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewDataBinding = DataBindingUtil.inflate(
            inflater, getLayoutId(), container, false)
        performDependencyInjection()
        return mViewDataBinding.root
    }

    private fun performDataBinding() {
        this.mViewModel = if (mViewModel == null) getViewModel() else mViewModel
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel)
        mViewDataBinding.executePendingBindings()
    }

    private fun performDependencyInjection() {
        AndroidSupportInjection.inject(this)
    }


    protected fun showSimpleAlertDialog(title: String?, message: String) {
        val builder = context?.let { AlertDialog.Builder(it) }
        title.let { builder?.setTitle(it) }
        builder?.setMessage(message)
        builder?.setPositiveButton("Ok", null)
        builder?.show()
    }

}