package com.github.githubtrend.injection.component

import dagger.Component
import com.github.githubtrend.injection.module.AppContextModule
import com.github.githubtrend.injection.module.NetworkModule
import com.github.githubtrend.view.git_detail.GitItemDetailActivity
import com.github.githubtrend.view.main.MainActivity
import javax.inject.Singleton

/**
 * Created by ali on 8/17/2018 AD.
 */
@Singleton
@Component(modules = arrayOf((NetworkModule::class), (AppContextModule::class)))
interface AppComponent {
    fun inject(activity: MainActivity)
    fun inject(activity: GitItemDetailActivity)
}