package com.deas.movieapps.custom

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.view.Window
import com.deas.movieapps.R

/**
 * Created by igede@awantunai.com on 19/06/21.
 */

class DialogMovie(context: Context) : Dialog(context) {

    private var message: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_progress)
        setCancelable(false)
        setCanceledOnTouchOutside(false)
    }

    fun setMessage(message: String) {
        this.message = message
    }

    class Builder(context: Context) {

        val dialog = DialogMovie(context)

        fun setMessage(message: String): Builder {
            dialog.setMessage(message)
            return this
        }

        fun build(): DialogMovie = dialog
    }
}