package com.deas.movieapps.network

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by igede@awantunai.com on 19/06/21.
 */

class HttpHeaderInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain?): Response? {
        val request = chain?.request()

        val builder = request?.newBuilder()
        return chain?.proceed(builder?.build())
    }
}