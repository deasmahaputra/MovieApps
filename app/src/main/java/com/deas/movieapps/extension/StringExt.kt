package com.deas.movieapps.extension

/**
 * Created by igede@awantunai.com on 19/06/21.
 */

fun String.takeIfNotEmpty(defValue: String): String {
    return if (this.isNotEmpty()) {
        this
    } else {
        defValue
    }
}