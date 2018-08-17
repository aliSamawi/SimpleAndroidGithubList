package github.com.githubtrend.injection.module

import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * module to represent context for all needed provides
 *
 * Created by ali on 8/17/2018 AD.
 */

@Module
class AppContextModule(val context: Context) {

    @Provides
    fun provideContext(): Context = context
}