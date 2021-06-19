package com.deas.movieapps.room

import com.deas.movieapps.room.dao.LocalDao
import com.deas.movieapps.room.entities.MovieListEntity
import com.google.gson.annotations.Since
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

/**
 * Created by igede@awantunai.com on 19/06/21.
 */

@Singleton
class LocalRepository(private var localDao: LocalDao,
                      private var compositeDisposable: CompositeDisposable
) {

    fun insertSkuData(movieListEntity: MovieListEntity) {
        compositeDisposable.add(
            Observable.fromCallable { localDao.insertList(movieListEntity) }
                .subscribeOn(Schedulers.computation())
                .subscribe()
        )
    }
}