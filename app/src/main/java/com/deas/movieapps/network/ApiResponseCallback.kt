package com.deas.movieapps.network

/**
 * Created by igede@awantunai.com on 19/06/21.
 */

interface ApiResponseCallback<T> {
    fun onSuccess(response: T)
    fun onError(error: NetworkError)
}