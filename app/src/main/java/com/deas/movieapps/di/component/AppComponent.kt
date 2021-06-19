package com.deas.movieapps.di.component

import android.app.Application
import com.deas.movieapps.base.MovieApplication
import com.deas.movieapps.di.builder.ActivityBuilder
import com.deas.movieapps.di.builder.FragmentBuilder
import com.deas.movieapps.di.module.AppModule
import com.deas.movieapps.di.module.NetworkModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

/**
 * Created by igede@awantunai.com on 19/06/21.
 */
@Singleton
@Component(modules = [(AndroidInjectionModule::class), (AppModule::class), (NetworkModule::class),
    (ActivityBuilder::class),(FragmentBuilder::class)])

interface AppComponent {

    fun inject(app: MovieApplication)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

}