package ir.r3za13.kotlin_rz_boilerplate.injection.module

import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * module to represent context for all needed provides
 *
 * @author Reza Abedini
 */
@Module
class AppContextModule(val context: Context) {

    @Provides
    fun provideContext(): Context = context
}