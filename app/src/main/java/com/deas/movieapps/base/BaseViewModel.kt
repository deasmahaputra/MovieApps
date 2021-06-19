package com.deas.movieapps.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

/**
 * Created by igede@awantunai.com on 19/06/21.
 */

abstract class BaseViewModel<N>: ViewModel(){

    private lateinit var navigator: WeakReference<N>
    val compositeDisposable = CompositeDisposable()

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }

    fun setNavigator(navigator: N) {
        this.navigator = WeakReference(navigator)
    }

    fun getNavigator(): N = navigator.get()!!
}