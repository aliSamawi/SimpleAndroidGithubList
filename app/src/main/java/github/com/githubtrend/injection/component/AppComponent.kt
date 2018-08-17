package github.com.githubtrend.injection.component

import dagger.Component
import github.com.githubtrend.view.main.MainActivity
import github.com.githubtrend.injection.module.AppContextModule
import github.com.githubtrend.injection.module.NetworkModule
import javax.inject.Singleton

/**
 * Created by ali on 8/17/2018 AD.
 */
@Singleton
@Component(modules = arrayOf((NetworkModule::class), (AppContextModule::class)))
interface AppComponent {
    fun inject(activity: MainActivity)
}