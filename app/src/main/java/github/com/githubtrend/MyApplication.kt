package github.com.githubtrend

import android.app.Application
import github.com.githubtrend.injection.component.AppComponent
import github.com.githubtrend.injection.module.AppContextModule

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
