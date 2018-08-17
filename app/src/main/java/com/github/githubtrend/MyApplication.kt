package com.github.githubtrend

import android.app.Application
import com.github.githubtrend.injection.component.AppComponent
import com.github.githubtrend.injection.component.DaggerAppComponent
import com.github.githubtrend.injection.module.AppContextModule

/**
 * Created by ali on 8/17/2018 AD.
 */
class MyApplication : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        setupComponent()
    }

    private fun setupComponent() {
        appComponent = DaggerAppComponent.builder()
                .appContextModule(AppContextModule(applicationContext))
                .build()
    }
}
