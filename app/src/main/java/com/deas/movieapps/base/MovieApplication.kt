package com.deas.movieapps.base

import android.app.Activity
import android.app.Application
import android.os.StrictMode
import androidx.appcompat.app.AppCompatDelegate
import com.deas.movieapps.di.component.AppComponent
import com.deas.movieapps.di.component.DaggerAppComponent
//import com.deas.movieapps.di.component.AppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject
//import com.deas.movieapps.di.component.DaggerAppComponent

/**
 * Created by igede@awantunai.com on 19/06/21.
 */

class MovieApplication : Application(), HasActivityInjector {

    lateinit var appComponent: AppComponent

    @Inject
    lateinit var activityDispatchingAndroidInjector : DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return activityDispatchingAndroidInjector
    }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        StrictMode.setVmPolicy(StrictMode.VmPolicy.Builder().build())
        DaggerAppComponent.builder().application(this).build().inject(this)
    }

}