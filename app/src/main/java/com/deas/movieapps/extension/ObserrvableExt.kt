package com.deas.movieapps.extension

import com.deas.movieapps.network.NetworkError
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by igede@awantunai.com on 19/06/21.
 */

fun <T : Any> Observable<T>.configured(): Observable<T> {
    return this.subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .onErrorResumeNext { throwable: Throwable -> Observable.error(throwable) }
}

fun <T : Any> Observable<T>.subscribe(onSuccess: (T) -> Unit, onError: (NetworkError) -> Unit): Disposable =
    this.subscribe({ response: T -> onSuccess(response) }, { throwable: Throwable -> onError(NetworkError(throwable)) })
